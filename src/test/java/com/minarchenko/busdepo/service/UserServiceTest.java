package com.minarchenko.busdepo.service;

import com.minarchenko.busdepo.model.User;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private UserService userService = new UserService();
    private DataSource dataSource = mock(DataSource.class);
    private Connection connection = mock(Connection.class);
    private PreparedStatement statement = mock(PreparedStatement.class);

    @Before
    public void setUp() throws Exception {
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
    }

    @Test
    public void getUsers() throws Exception {

        ResultSet resultSet = mock(ResultSet.class);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("user_name")).thenReturn("user_name_1");
        when(resultSet.getString("login")).thenReturn("login_1");
        when(resultSet.getString("password")).thenReturn("password_1");
        when(resultSet.getString("user_role")).thenReturn("user_role_1");

        List<User> users = userService.getUsers(dataSource, 1);

        verify(statement).setInt(1, 2);
        verify(statement).setInt(2, 0);

        User user = new User(1, "user_name_1","login_1",
                "password_1","user_role_1");
        List<User> expected = Collections.singletonList(user);
        assertEquals(expected, users);
    }


    @Test
    public void addUser() throws Exception {

        userService.addUser("user_name_1","login_1",
                "password_1","user_role_1", dataSource);

        verify(statement).setString(1, "user_name_1");
        verify(statement).setString(2, "login_1");
        /**
         * Is not possible to verify, because hashcode is unique each time
         */
//        verify(statement).setString(3, "66cbef2a2a15475a89d7e39a05e635e1023238df43ee1231348556b09f490490$1$4e99adad84c57db958199efeebe5111407fa599b3ae7da382edba286a17007f0");
        verify(statement).setString(4, "user_role_1");
        verify(statement).execute();
    }

    @Test
    public void userDelete() throws Exception {
        userService.userDelete("user_id_1", dataSource);

        verify(statement).setString(1, "user_id_1");
        verify(statement).execute();
    }

    @Test
    public void countUsersPages() throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getDouble(1)).thenReturn(7.0);

        int countUsersPages = userService.countUsersPages(dataSource);

        assertEquals(4, countUsersPages);
    }

}