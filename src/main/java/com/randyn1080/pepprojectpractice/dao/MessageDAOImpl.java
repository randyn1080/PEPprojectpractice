package com.randyn1080.pepprojectpractice.dao;

import com.randyn1080.pepprojectpractice.model.Message;
import com.randyn1080.pepprojectpractice.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAOImpl implements MessageDAO {
    @Override
    public Message createMessage(Message message) {
        String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?,?,?);";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = ConnectionUtil.getConnection();
            pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, message.getPosted_by());
            pstmt.setString(2, message.getMessage_text());
            pstmt.setLong(3, message.getTime_posted_epoch());

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int generatedMessageId = rs.getInt(1);
                message.setMessage_id(generatedMessageId);
                return message;
            }
        } catch (SQLException e) {
            //TODO: handle exception
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    //TODO: handle exception
                }
            }

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    //TODO: handle exception
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //TODO: handle exception
                }
            }
        }
        return null;
    }

    @Override
    public List<Message> getAllMessages() {
        String sql = "SELECT * FROM message;";
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<Message> messages = new ArrayList<>();

        try {
            connection = ConnectionUtil.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Message message = new Message();
                message.setMessage_id(rs.getInt("message_id"));
                message.setPosted_by(rs.getInt("posted_by"));
                message.setMessage_text(rs.getString("message_text"));
                message.setTime_posted_epoch(rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        } catch (SQLException e) {
            //TODO: handle exception
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    //TODO: handle exception
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    //TODO: handle exception
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //TODO: handle exception
                }
            }
        }
        return messages;
    }

    @Override
    public List<Message> getAllMessagesByAccountId(int accountId) {
        return List.of();
    }

    @Override
    public Message getMessageById(int msgId) {
        String sql = "SELECT * FROM message WHERE message_id = ?;";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = ConnectionUtil.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, msgId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Message message = new Message();
                message.setMessage_id(msgId);
                message.setPosted_by(rs.getInt("posted_by"));
                message.setMessage_text(rs.getString("message_text"));
                message.setTime_posted_epoch(rs.getLong("time_posted_epoch"));
                return message;
            }

        } catch (SQLException e) {
            //TODO: handle exception
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    //TODO: handle exception
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    //TODO: handle exception
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //TODO: handle exception
                }
            }
        }

        return null;
    }

    @Override
    public Message updateMessageText(int msgId, String newText) {
        String sql = "UPDATE message SET message_text = ? WHERE message_id = ?;";
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = ConnectionUtil.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, newText);
            pstmt.setInt(2, msgId);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return getMessageById(msgId);
            }

        } catch (SQLException e) {
            //TODO: handle exception
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    // TODO: handle exception
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //TODO: handle exception
                }
            }
        }

        return null;
    }

    @Override
    public Boolean deleteMessage(int msgId) {
        String sql = "DELETE FROM message WHERE message_id = ?;";
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = ConnectionUtil.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, msgId);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return true;
            }
        } catch (SQLException e) {
            //TODO: handle exception
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    //TODO: handle exception
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //TODO: handle exception
                }
            }
        }

        return false;
    }
}
