package server.repository.manager;

import server.repository.manager.exception.DuplicateException;
import server.repository.manager.exception.NotFoundException;
import server.repository.model.RegisterData;

import java.sql.*;

public class RegisterManager extends TableManager<RegisterData> {
    private static final String accountDuplicate = "[SQLITE_CONSTRAINT_UNIQUE] A UNIQUE constraint failed (UNIQUE constraint failed: register.account)";

    @Override
    public int add(RegisterData newData) throws DuplicateException {
        String sql = "insert into register (account, password, state) values(?,?,?)";
        int id = 0;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, newData.getAccount());
            pstmt.setString(2, newData.getPassword());
            pstmt.setInt(3, newData.getState());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getInt(1);  // 获取生成的 ID
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to register account " + newData.getAccount());
            System.out.println("Reason: " + e.getMessage());
            if (e.getMessage().equals(accountDuplicate)) {
                throw new DuplicateException("Account already exists", DuplicateException.ACCOUNT);
            }
            return 0;
        }

        System.out.println("Register new account: " + newData.getAccount());
        return id;
    }

    @Override
    public boolean update(RegisterData newData) throws DuplicateException, NotFoundException {
        String sql = "update register set account=?, password=?, state=? where id=?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newData.getAccount());
            pstmt.setString(2, newData.getPassword());
            pstmt.setInt(3, newData.getState());
            pstmt.setInt(4, newData.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failed to update account " + newData.getAccount());
            System.out.println("Reason: " + e.getMessage());
            if (e.getMessage().equals(accountDuplicate)) {
                throw new DuplicateException("Account already exists", DuplicateException.ACCOUNT);
            }
            return false;
        }

        System.out.println("Update register information with id: " + newData.getId());
        return true;
    }

    public boolean updateState(int id, int state) {
        String sql = "update register set state=? where id=?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, state);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failed to update state for user id: " + id);
            System.out.println("Reason: " + e.getMessage());
            return false;
        }

        System.out.println("Update user state to " + state + " with id: " + id);
        return true;
    }


    @Override
    public boolean delete(int id) {
        String sql = "delete from register where id=?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failed to delete account " + id);
            System.out.println("Reason: " + e.getMessage());
            return false;
        }

        System.out.println("Delete register information with id: " + id);
        return true;
    }

    @Override
    public RegisterData get(int id) throws NotFoundException {
        String sql = "select * from register where id=?";
        RegisterData registerData = null;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String account = rs.getString("account");
                String password = rs.getString("password");
                int state = rs.getInt("state");
                registerData = new RegisterData(id, account, password, state);
            } else {
                throw new NotFoundException("Register information not found", id);
            }

        } catch (SQLException e) {
            System.out.println("Failed to select account " + id);
            System.out.println("Reason: " + e.getMessage());
            return null;
        }

        System.out.println("Get register information with id: " + id);
        return registerData;
    }

    public RegisterData get(String account) throws NotFoundException {
        String sql = "select * from register where account = ?";
        RegisterData registerData = null;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, account);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String password = rs.getString("password");
                int state = rs.getInt("state");
                registerData = new RegisterData(id, account, password, state);
            } else {
                System.out.println("Failed to select account: " + account);
                System.out.println("Reason: Register information not found");
                throw new NotFoundException("Register information not found", account);
            }

        } catch (SQLException e) {
            System.out.println("Failed to select account: " + account);
            System.out.println("Reason: " + e.getMessage());
            return null;
        }

        System.out.println("Get register information with account: " + account);
        return registerData;
    }
}
