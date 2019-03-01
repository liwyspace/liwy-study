package com.liwy.study.springboot.dao.impl;

import com.liwy.study.springboot.dao.IUserDao;
import com.liwy.study.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/2 17:41 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Repository("userDaoJdbcImpl")
public class UserDaoJdbcImpl implements IUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long getUserCount() {
        return jdbcTemplate.queryForObject("select count(*) from liwy_user", Long.class);
    }

    @Override
    public List<User> queryUserList() {
        return jdbcTemplate.query("SELECT id, username, sex, email, register_time, status, content, icon FROM liwy_user", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setUsername(resultSet.getString(2));
                user.setSex(resultSet.getByte(3));
                user.setEmail(resultSet.getString(4));
                user.setRegisterTime(resultSet.getTimestamp(5));
                user.setStatus(resultSet.getByte(6));

//                user.setContent(resultSet.getString(7));
                DefaultLobHandler lobHandler = new DefaultLobHandler();
                user.setContent(lobHandler.getClobAsString(resultSet, 7)); // CLOB处理方式

//                user.setIcon(resultSet.getBytes(8));
                user.setIcon(lobHandler.getBlobAsBytes(resultSet, 8)); // BLOB处理方式
                return user;
            }
        });
    }

    @Override
    public User getUser(Long id) {
        return jdbcTemplate.queryForObject("select * from liwy_user where id=?", new BeanPropertyRowMapper<>(User.class), id);
    }

    @Override
    public String getUserName(Long id) {
        return jdbcTemplate.queryForObject("select username from liwy_user where id=?",String.class, id);
    }

    @Override
    public void updateUser(User user) {
        this.jdbcTemplate.update(
                "update liwy_user set username=?, sex=?, email=?, register_time=? where id = ?", user.getUsername(), user.getSex(),user.getEmail(), user.getRegisterTime(),user.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(
                "delete from liwy_user where id = ?",id);
    }

    @Override
    public int insertUser(User user) {
//        jdbcTemplate.update("insert into liwy_user(username, sex, email, register_time, status, content, icon) VALUE (?,?,?,?,?,?,?)", user.getUsername(), user.getSex(),user.getEmail(), user.getRegisterTime(),user.getStatus(),user.getContent(),user.getIcon());

        DefaultLobHandler lobHandler = new DefaultLobHandler();
        int result = jdbcTemplate.execute("INSERT INTO liwy_user(username, sex, email, register_time, status, content, icon) VALUE (?,?,?,?,?,?,?)", new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
            @Override
            protected void setValues(PreparedStatement preparedStatement, LobCreator lobCreator) throws SQLException, DataAccessException {
                preparedStatement.setString(1,user.getUsername());
                preparedStatement.setByte(2, user.getSex());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setTimestamp(4, new Timestamp(user.getRegisterTime().getTime()));
                preparedStatement.setByte(5, user.getStatus());
//                try {
//                    preparedStatement.setClob(6, new FileReader(""));
//                    preparedStatement.setBlob(7, new FileInputStream(""));
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }

                lobCreator.setClobAsString(preparedStatement, 6, user.getContent());
                lobCreator.setBlobAsBytes(preparedStatement, 7, user.getIcon());

            }
        });

        return result;
    }

    @Override
    public void insertUserGetId(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO liwy_user(username, sex, email, register_time, status, content, icon) VALUE (?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1,user.getUsername());
                ps.setByte(2, user.getSex());
                ps.setString(3, user.getEmail());
                ps.setTimestamp(4, new Timestamp(user.getRegisterTime().getTime()));
                ps.setByte(5, user.getStatus());
                ps.setClob(6, new StringReader(user.getContent()));
                ps.setBlob(7, new ByteArrayInputStream(user.getIcon()));
                return ps;
            }
        }, keyHolder);
        System.out.println("插入User的主键为："+keyHolder.getKey().longValue());
    }

    @Override
    public void batchUserList(List<User> users) {
//        List<Object[]> batch = new ArrayList<Object[]>();
//        for (User user : users) {
//            Object[] values = new Object[] {
//                    user.getUsername(),user.getSex(),user.getEmail(),user.getRegisterTime(),user.getId()};
//            batch.add(values);
//        }
//        int[] updateCounts = jdbcTemplate.batchUpdate(
//                "UPDATE liwy_user SET username=?, sex=?, email=?, register_time=? WHERE id = ?",
//                batch);


        jdbcTemplate.batchUpdate("UPDATE liwy_user SET username=?, sex=?, email=?, register_time=? WHERE id = ?", users, 20, new ParameterizedPreparedStatementSetter<User>() {
            @Override
            public void setValues(PreparedStatement preparedStatement, User user) throws SQLException {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setByte(2, user.getSex());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setTimestamp(4, new Timestamp(user.getRegisterTime().getTime()));
                preparedStatement.setLong(5, user.getId());
            }
        });
    }

    @Override
    public void batchInsert(List<User> users) {
//        jdbcTemplate.batchUpdate("INSERT INTO liwy_user(username, sex, email, register_time, status, content, icon) VALUE (?,?,?,?,?,?,?)", new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
//                User user = users.get(i);
//                preparedStatement.setString(1, user.getUsername());
//                preparedStatement.setByte(2, user.getSex());
//                preparedStatement.setString(3, user.getEmail());
//                preparedStatement.setTimestamp(4, new Timestamp(user.getRegisterTime().getTime()));
//                preparedStatement.setByte(5, user.getStatus());
//                preparedStatement.setClob(6, new StringReader(user.getContent()));
//                preparedStatement.setBlob(7, new ByteArrayInputStream(user.getIcon()));
//            }
//
//            @Override
//            public int getBatchSize() {
//                return users.size();
//            }
//        });

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(users.toArray());
        int[] updateCounts = namedParameterJdbcTemplate.batchUpdate(
                "INSERT INTO liwy_user(username, sex, email, register_time, status, content, icon) VALUE (:username,:sex,:email,:registerTime,:status,:content,:icon)",
                batch);
    }

    @Override
    public void getEntityCount(Integer chId) {
        List result = jdbcTemplate.execute(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection connection) throws SQLException {
                CallableStatement callableStatement = connection.prepareCall("{call procGetEntityCount(?, ?, ?)}");
                callableStatement.setInt(1, 1000006);
                callableStatement.registerOutParameter(2, Types.BIGINT);
                callableStatement.registerOutParameter(3, Types.BIGINT);
                return callableStatement;
            }
        }, new CallableStatementCallback<List>() {
            @Override
            public List doInCallableStatement(CallableStatement callableStatement) throws SQLException, DataAccessException {
                callableStatement.execute();
                Long cNum = callableStatement.getLong(2);
                Long tagNum = callableStatement.getLong(3);

                return Arrays.asList(cNum, tagNum);
            }
        });

        System.out.println(result);
    }

    @Override
    public void simpleInsertUser(User user) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("liwy_user").usingGeneratedKeyColumns("id");
//        Map<String, Object> param = new HashMap<>();
//        param.put("username", user.getUsername());
//        param.put("sex", user.getSex());
//        param.put("email", user.getEmail());
//        param.put("register_time", user.getRegisterTime());
//        param.put("status", user.getStatus());
//        param.put("content", user.getContent());
//        param.put("icon", user.getIcon());
//        Long id = (Long) simpleJdbcInsert.executeAndReturnKey(param);

        Long id = (Long) simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(user));
        System.out.println(id);
    }

    @Override
    public void simpleGetEntityCount(Integer chId) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("procGetEntityCount");
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("channel_id", chId);
        Map<String, Object> execute = simpleJdbcCall.execute(sqlParameterSource);
        System.out.println((Long) execute.get("content_num"));
        System.out.println((Long) execute.get("tag_num"));
    }

    @Override
    public void simpleQueryChannelTag() {
    }

    @Override
    public List<User> selectUser(User param) {
        return null;
    }
}
