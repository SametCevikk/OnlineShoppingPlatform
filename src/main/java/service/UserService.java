package service;

import dao.UserDAO;
import exception.ExceptionMassagesConstant;
import exception.OnlineStoreException;
import model.User;
import model.enums.Role;
import util.PasswordUtil;

public class UserService {

    private final UserDAO userDAO;

    public UserService(){
        userDAO=new UserDAO();
    }

   public void save(String userName,String password,Role role) throws OnlineStoreException {

        User foundUser= userDAO.findByUserName(userName);

        if(foundUser != null){
            throw new OnlineStoreException(ExceptionMassagesConstant.USER_EMAIL_ALREADY_EXIST);
        }

        userDAO.save(new User(userName,PasswordUtil.hash(password), role));
        System.out.println("Register successful");
   }

    public User login(String userName, String password) throws OnlineStoreException {
        User foundUser=userDAO.findByUserName(userName);

        if(foundUser !=null){
            String hashedPassword=PasswordUtil.hash(password);
            if(!hashedPassword.equals(foundUser.getPassword())){
                throw new OnlineStoreException(ExceptionMassagesConstant.USER_PASSWORD_DOES_NOT_MATCH);
            }
        }else {
            throw new OnlineStoreException(ExceptionMassagesConstant.USER_PASSWORD_DOES_NOT_MATCH);
        }

        System.out.println("Login successful");
        System.out.println("Welcome " + foundUser.getUserName());
        return foundUser;
    }

}
