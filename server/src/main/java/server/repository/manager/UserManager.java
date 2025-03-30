package server.repository.manager;

import server.repository.manager.exception.DuplicateException;
import server.repository.manager.exception.NotFoundException;
import server.repository.model.UserData;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserManager extends TableManager<UserData> {
    private final String accountDuplicate = "[SQLITE_CONSTRAINT_UNIQUE] A UNIQUE constraint failed (UNIQUE constraint failed: user.account)";
    private final String idDuplicate = "[SQLITE_CONSTRAINT_UNIQUE] A UNIQUE constraint failed (UNIQUE constraint failed: user.id)";

    @Override
    public int add(UserData newData) throws DuplicateException {
        String sql = "INSERT INTO user (id, username, account, last_login, register_time) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newData.getId());
            pstmt.setString(2, newData.getUsername());
            pstmt.setString(3, newData.getAccount());
            pstmt.setString(4, newData.getLastLoginTime().toString());
            pstmt.setString(5, newData.getRegistrationDate().toString());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failed to add new user id: " + newData.getId());
            System.out.println("Reason: " + e.getMessage());
            //SQLite怎么更好地区分唯一约束冲突和单纯的操作失败？？
            if (e.getMessage().equals(accountDuplicate)) {
                throw new DuplicateException("Account duplicated");
            }
            if (e.getMessage().equals(idDuplicate)) {
                throw new DuplicateException("Id duplicated", DuplicateException.ID);
            }
        }

        System.out.println("Add new user id: " + newData.getId());
        return newData.getId();
    }

    @Override
    public boolean update(UserData newData) throws DuplicateException, NotFoundException {
        String sql = "UPDATE user SET username = ?, account = ?, last_login = ?, register_time = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newData.getUsername());
            pstmt.setString(2, newData.getAccount());
            pstmt.setString(3, newData.getLastLoginTime().toString());
            pstmt.setString(4, newData.getRegistrationDate().toString());
            pstmt.setInt(5, newData.getId());
            if (pstmt.executeUpdate() == 0) {
                throw new NotFoundException("User not found", newData.getId());
            }

        } catch (SQLException e) {
            System.out.println("Failed to update user id: " + newData.getId());
            System.out.println("Reason: " + e.getMessage());

            if (e.getMessage().equals(accountDuplicate)) {
                throw new DuplicateException("Account duplicated", DuplicateException.ACCOUNT);
            }
            if (e.getMessage().equals(idDuplicate)) {
                throw new DuplicateException("Id duplicated", DuplicateException.ID);
            }
            return false;
        }

        System.out.println("Update user id: " + newData.getId());
        return true;
    }

    public boolean updateUsername(int id, String newUsername) throws NotFoundException {
        String sql = "UPDATE user SET username = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newUsername);
            pstmt.setInt(2, id);
            if (pstmt.executeUpdate() == 0) {
                throw new NotFoundException("User not found", id);
            }

        } catch (SQLException e) {
            System.out.println("Failed to update username for user id: " + id);
            System.out.println("Reason: " + e.getMessage());
            return false;
        }

        System.out.println("Update username for user id: " + id);
        return true;
    }

    public boolean updateAccount(int id, String newAccount) throws DuplicateException, NotFoundException {
        String sql = "UPDATE user SET account = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newAccount);
            pstmt.setInt(2, id);
            if (pstmt.executeUpdate() == 0) {
                throw new NotFoundException("User not found", id);
            }

        } catch (SQLException e) {
            System.out.println("Failed to update account for user id: " + id);
            System.out.println("Reason: " + e.getMessage());

            if (e.getMessage().equals(accountDuplicate)) {
                throw new DuplicateException("Account duplicated", DuplicateException.ACCOUNT);
            }
            return false;
        }

        System.out.println("Update account for user id: " + id);
        return true;
    }

    public boolean updateLastLoginTime(int id, LocalDateTime newLastLoginTime) throws NotFoundException {
        String sql = "UPDATE user SET last_login = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newLastLoginTime.toString());
            pstmt.setInt(2, id);
            if (pstmt.executeUpdate() == 0) {
                throw new NotFoundException("User not found", id);
            }

        } catch (SQLException e) {
            System.out.println("Failed to update user last login time for user id: " + id);
            System.out.println("Reason: " + e.getMessage());
            return false;
        }

        System.out.println("Update user last login time for user id: " + id);
        return true;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM user WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failed to delete user id: " + id);
            System.out.println("Reason: " + e.getMessage());
            return false;
        }

        System.out.println("Delete user id: " + id);
        return true;
    }

    @Override
    public UserData get(int id) throws NotFoundException {
        UserData userData = null;
        String sql = "SELECT username, account, last_login, register_time FROM user WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                String account = rs.getString("account");
                String lastLogin = rs.getString("last_login");
                String registrationTime = rs.getString("register_time");
                userData = new UserData(
                        id, username, account,
                        LocalDateTime.parse(lastLogin),
                        LocalDate.parse(registrationTime)
                );
            } else {
                throw new NotFoundException("User not found", id);
            }

        } catch (SQLException e) {
            System.out.println("Failed to get user id: " + id);
            System.out.println("Reason: " + e.getMessage());
            return null;
        }

        System.out.println("Get user id: " + id);
        return userData;
    }
}
