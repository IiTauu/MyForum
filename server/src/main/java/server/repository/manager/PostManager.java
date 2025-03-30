package server.repository.manager;

import server.repository.manager.exception.DuplicateException;
import server.repository.manager.exception.NotFoundException;
import server.repository.model.PostData;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostManager extends TableManager<PostData> {

    /**
     *
     * @param newData
     * @return 数据库为post分配的id
     * @throws DuplicateException
     */
    @Override
    public int add(PostData newData) throws DuplicateException {
        String sql = "INSERT INTO post (title, content, author_id, father_id, post_time, comment_count, thumb_count) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int id = 0;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newData.getTitle());
            pstmt.setString(2, newData.getContent());
            pstmt.setInt(3, newData.getAuthorId());
            pstmt.setInt(4, newData.getFatherId());
            pstmt.setString(5, newData.getPostTime().toString());
            pstmt.setInt(6, newData.getCommentCount());
            pstmt.setInt(7, newData.getThumbCount());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next())
                id = rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("Failed to add post");
            System.out.println("Reason: " + e.getMessage());
            return 0;
        }

        System.out.println("Add new post id: " + id);
        return id;
    }

    @Override
    public boolean update(PostData newData) throws DuplicateException, NotFoundException {
        String sql = "UPDATE post SET title = ?, content = ?, post_time = ?, comment_count = ?, thumb_count = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newData.getTitle());
            pstmt.setString(2, newData.getContent());
            pstmt.setString(3, newData.getPostTime().toString());
            pstmt.setInt(4, newData.getCommentCount());
            pstmt.setInt(5, newData.getThumbCount());
            pstmt.setInt(6, newData.getId());
            if (pstmt.executeUpdate() == 0) {
                throw new NotFoundException("Post not found", newData.getId());
            }

        } catch (SQLException e) {
            System.out.println("Failed to update post id: " + newData.getId());
            System.out.println("Reason: " + e.getMessage());
            return false;
        }

        System.out.println("Update post id: " + newData.getId());
        return true;
    }

    public boolean increaseCommentCount(int id) throws NotFoundException {
        String sql = "UPDATE post SET comment_count = comment_count + 1 WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            if (pstmt.executeUpdate() == 0) {
                throw new NotFoundException("Post not found", id);
            }

        } catch (SQLException e) {
            System.out.println("Failed to update post id: " + id);
            System.out.println("Reason: " + e.getMessage());
            return false;
        }

        System.out.println("Update post id: " + id);
        return true;
    }

    public boolean decreaseCommentCount(int id) throws NotFoundException {
        String sql = "UPDATE post SET comment_count = comment_count - 1 WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            if (pstmt.executeUpdate() == 0) {
                throw new NotFoundException("Post not found", id);
            }

        } catch (SQLException e) {
            System.out.println("Failed to update post id: " + id);
            System.out.println("Reason: " + e.getMessage());
            return false;
        }

        System.out.println("Update post id: " + id);
        return true;
    }

    public boolean increaseThumbCount(int id) throws NotFoundException {
        String sql = "UPDATE post SET thumb_count = thumb_count + 1 WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            if (pstmt.executeUpdate() == 0) {
                throw new NotFoundException("Post not found", id);
            }

        } catch (SQLException e) {
            System.out.println("Failed to update post id: " + id);
            System.out.println("Reason: " + e.getMessage());
            return false;
        }

        System.out.println("Update post id: " + id);
        return true;
    }

    public boolean decreaseThumbCount(int id) throws NotFoundException {
        String sql = "UPDATE post SET thumb_count = thumb_count - 1 WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            if (pstmt.executeUpdate() == 0) {
                throw new NotFoundException("Post not found", id);
            }

        } catch (SQLException e) {
            System.out.println("Failed to update post id: " + id);
            System.out.println("Reason: " + e.getMessage());
            return false;
        }

        System.out.println("Update post id: " + id);
        return true;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM post WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failed to delete post id: " + id);
            System.out.println("Reason: " + e.getMessage());
            return false;
        }

        System.out.println("Delete post id: " + id);
        return true;
    }

    /**
     *
     * @param id
     * @return 返回null但未抛出异常说明操作失败
     * @throws NotFoundException
     */
    @Override
    public PostData get(int id) throws NotFoundException {
        String sql = "SELECT * FROM post WHERE id = ?";
        PostData postData = null;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");
                String postTime = rs.getString("post_time");
                int commentCount = rs.getInt("comment_count");
                int thumbCount = rs.getInt("thumb_count");
                int authorId = rs.getInt("author_id");
                int fatherId = rs.getInt("father_id");
                postData = new PostData(
                        id, title, content,
                        authorId, fatherId,
                        LocalDateTime.parse(postTime),
                        commentCount, thumbCount
                );
            } else {
                throw new NotFoundException("Post not found", id);
            }

        } catch (SQLException e) {
            System.out.println("Failed to get post id: " + id);
            System.out.println("Reason: " + e.getMessage());
            return null;
        }

        System.out.println("Get post id: " + id);
        return postData;
    }

    /**
     * 用于首次获取多个post
     * @param fatherId
     * @param count
     * @return List<PostData>
     */
    public List<PostData> getPosts(int fatherId, int count) {
        String sql = "SELECT * FROM post WHERE father_id = ? ORDER BY id DESC LIMIT ?";
        List<PostData> posts = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, fatherId);
            pstmt.setInt(2, count);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String postTime = rs.getString("post_time");
                int commentCount = rs.getInt("comment_count");
                int authorId = rs.getInt("author_id");
                int thumbCount = rs.getInt("thumb_count");
                posts.add(new PostData(
                        id, title, content,
                        authorId, fatherId,
                        LocalDateTime.parse(postTime),
                        commentCount, thumbCount
                ));
            }

        } catch (SQLException e) {
            System.out.println("Failed to get posts");
            System.out.println("Reason: " + e.getMessage());
            return null;
        }

        System.out.println("Get posts count: " + count);
        return posts;
    }

    /**
     * 从上一次获取到的最后一个post开始往后返回
     * @param fatherId
     * @param count
     * @param lastId
     * @return List<PostData>
     */
    public List<PostData> getPosts(int fatherId, int count, int lastId) {
        String sql = "SELECT * FROM post WHERE father_id = ? AND id < ? ORDER BY id DESC LIMIT ?";
        List<PostData> posts = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, fatherId);
            pstmt.setInt(2, lastId);
            pstmt.setInt(3, count);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String postTime = rs.getString("post_time");
                int commentCount = rs.getInt("comment_count");
                int authorId = rs.getInt("author_id");
                int thumbCount = rs.getInt("thumb_count");
                posts.add(new PostData(
                        id, title, content,
                        authorId, fatherId,
                        LocalDateTime.parse(postTime),
                        commentCount, thumbCount
                ));
            }

        } catch (SQLException e) {
            System.out.println("Failed to get posts");
            System.out.println("Reason: " + e.getMessage());
            return null;
        }

        System.out.println("Get posts count: " + count);
        return posts;
    }

}
