package com.example.m3_userjspservletsql.controller;

import com.example.m3_userjspservletsql.dao.UserDao;
import com.example.m3_userjspservletsql.model.model.User;
import com.sun.net.httpserver.HttpPrincipal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public void init(){
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = request.getParameter("action");
        if (action == null){
            action="";
        }
        try {
            switch (action){
                case "create":
                    insertUser(request,response);
                    break;
                case "edit":
                    updateUser(request,response);
                    break;
            }
        }catch (SQLException ex){
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        String action = request.getParameter("action");
        if (action == null){
            action ="";
        }
        try {
            switch (action){
                case "create" :
                    showNewForm(request,response);
                    break;
                case "edit":
                    showEditForm(request,response);
                    break;
                case "delete":
                    deleteUser(request,response);
                    break;

                default:
                    listUser(request,response);
                    break;
            }
        } catch (SQLException ex){
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException,ServletException{
        List<User> listUser = userDao.selectAllUser();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        dispatcher.forward(request,response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(request,response);
    }

    private void showEditForm(HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException,ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDao.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request,response);
    }


private void insertUser(HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException,ServletException{
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        User user = new User(name,email,country)    ;
        userDao.insertUser(user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(request,response);
}

private void updateUser(HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException,ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User book = new User(name,email,country);
        userDao.update(book);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
        dispatcher.forward(request,response);
}

private void deleteUser(HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException,ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        userDao.delete(id);

        List<User> listUser = userDao.selectAllUser();
        request.setAttribute("listUser",listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
                dispatcher.forward(request,response);
}
}
