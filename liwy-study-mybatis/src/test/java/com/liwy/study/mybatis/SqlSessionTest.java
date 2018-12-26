package com.liwy.study.mybatis;

import com.liwy.study.mybatis.bo.ContentBo;
import com.liwy.study.mybatis.dao.IContentDao;
import com.liwy.study.mybatis.entity.Content;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Ignore;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SqlSessionTest {

    /**
     * <b>描述：</b> 通过程序构建SqlSessionFactory<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     * <p>
     * SqlSessionFactoryBuilder
     * 这个类可以被实例化、使用和丢弃，一旦创建了 SqlSessionFactory，就不再需要它了。因此 SqlSessionFactoryBuilder 实例的
     * 最佳作用域是方法作用域（也就是局部方法变量）。你可以重用 SqlSessionFactoryBuilder 来创建多个 SqlSessionFactory 实例，
     * 但是最好还是不要让其一直存在以保证所有的 XML 解析资源开放给更重要的事情。
     * SqlSessionFactory
     * SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，没有任何理由对它进行清除或重建。使用 SqlSessionFactory 的最佳实践是
     * 在应用运行期间不要重复创建多次，多次重建 SqlSessionFactory 被视为一种代码“坏味道（bad smell）”。因此 SqlSessionFactory 的
     * 最佳作用域是应用作用域。有很多方法可以做到，最简单的就是使用单例模式或者静态单例模式。
     * SqlSession
     * 每个线程都应该有它自己的 SqlSession 实例。SqlSession 的实例不是线程安全的，因此是不能被共享的，所以它的最佳的作用域
     * 是请求或方法作用域。绝对不能将 SqlSession 实例的引用放在一个类的静态域，甚至一个类的实例变量也不行。
     * 也绝不能将 SqlSession 实例的引用放在任何类型的管理作用域中，比如 Servlet 架构中的 HttpSession。如果你现在正在使用一种 Web 框架，
     * 要考虑 SqlSession 放在一个和 HTTP 请求对象相似的作用域中。换句话说，每次收到的 HTTP 请求，就可以打开一个 SqlSession，返回一个响应，
     * 就关闭它。这个关闭操作是很重要的，你应该把这个关闭操作放到 finally 块中以确保每次都能执行关闭。
     * </p>
     * <p>
     * selectOne 和 selectList 的不同仅仅是 selectOne 必须返回一个对象或 null 值。如果返回值多于一个，那么就会抛出异常。
     * 如果你不知道返回对象的数量，请使用 selectList。如果需要查看返回对象是否存在，可行的方案是返回一个值即可（0 或 1）。
     * selectMap 稍微特殊一点，因为它会将返回的对象的其中一个属性作为 key 值，将对象作为 value 值，从而将多结果集转为 Map 类型值。
     * </p>
     *
     * @param
     * @return void
     */
    @Test
    public void testBuildByJava() throws PropertyVetoException, IOException {
        // 加载配置文件
        Properties properties = new Properties();
        properties.load(SqlSessionTest.class.getResourceAsStream("/config-jdbc.properties"));

        // 创建c3p0数据源
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(properties.getProperty("db_driver"));
        dataSource.setJdbcUrl(properties.getProperty("db_url"));
        dataSource.setUser(properties.getProperty("db_username"));
        dataSource.setPassword(properties.getProperty("db_password"));
        dataSource.setMinPoolSize(5);
        dataSource.setAcquireIncrement(5);
        dataSource.setMaxPoolSize(20);

        // 创建Jdbc事务工厂
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        // 设置settings
        configuration.setMapUnderscoreToCamelCase(true); // 开启自动驼峰命名规则映射
        // 设置别名
        configuration.getTypeAliasRegistry().registerAlias(Content.class);
        configuration.getTypeAliasRegistry().registerAlias(ContentBo.class);
        // 添加mapper
        configuration.addMapper(IContentDao.class); // 默认会查询同包下同名xml文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        // 获取Session
        SqlSession session = sqlSessionFactory.openSession();

        // 查询数据
        try {
            // 通过session方式调用
            Content content = (Content) session.selectOne("com.liwy.study.mybatis.dao.IContentDao.getContent", 1L);
            System.out.println(content);

            // 通过接口方式调用
            IContentDao contentDao = session.getMapper(IContentDao.class);
            System.out.println(contentDao.getContent(1L));
        } finally {
            // 关闭Session
            session.close();
        }

    }

    /**
     * <b>描述：</b> 通过XML构建SqlSessionFactory<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testBuildByXML() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml"); // 从ClassPath中获取配置文件流
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取Session
        SqlSession session = sqlSessionFactory.openSession();

        // 向 autoCommit 可选参数传递 true 值即可开启自动提交功能。
        // SqlSession session1 = sqlSessionFactory.openSession(true);

        // 设置事务隔离级别
        // NONE、READ_UNCOMMITTED、READ_COMMITTED、REPEATABLE_READ 和 SERIALIZABLE
        // SqlSession session2 = sqlSessionFactory.openSession(TransactionIsolationLevel.READ_UNCOMMITTED);

        // 设置执行器类型
        // ExecutorType.SIMPLE：这个执行器类型不做特殊的事情。它为每个语句的执行创建一个新的预处理语句。
        // ExecutorType.REUSE：这个执行器类型会复用预处理语句。
        // ExecutorType.BATCH：这个执行器会批量执行所有更新语句，如果 SELECT 在它们中间执行，必要时请把它们区分开来以保证行为的易读性。
        // SqlSession session3 = sqlSessionFactory.openSession(ExecutorType.BATCH);

        // 查询数据
        try {
            // 通过接口方式调用
            IContentDao contentDao = session.getMapper(IContentDao.class);
            System.out.println(contentDao.getContent(1L));
            System.out.println(contentDao.getContentMap(1L));

        } finally {
            // 关闭Session
            session.close();
        }
    }
}
