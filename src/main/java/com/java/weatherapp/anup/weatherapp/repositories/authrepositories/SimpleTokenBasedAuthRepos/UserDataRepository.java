package com.java.weatherapp.anup.weatherapp.repositories.authrepositories.SimpleTokenBasedAuthRepos;

import com.java.weatherapp.anup.weatherapp.dtos.authDTOs.LoginRequestDTO;
import com.java.weatherapp.anup.weatherapp.models.weatherapimodels.authModels.*;
import com.java.weatherapp.anup.weatherapp.models.weatherapimodels.authModels.Role;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class UserDataRepository {
    private HashMap<String,Session> loggedInusersToken=new HashMap<>();
    private HashMap<Session, UserRole> loggedInUsers = new HashMap<>();
    private HashSet<UserRole> registeredUsers = new HashSet<>();

    public void adduserSessioninMap(UserRole userrole,Session session) {
        if(loggedInUsers.containsKey(session)) {
            loggedInUsers.remove(session);
        }
        loggedInUsers.put(session, userrole);
        if(!registeredUsers.contains(userrole)) {
            registeredUsers.add(userrole);
        }
        if(!loggedInusersToken.containsKey(session.getToken()))
            loggedInusersToken.put(session.getToken(),session);
        return;
    }
    public void removeUserinSessionMaps(String token)
    {
        if(!loggedInusersToken.containsKey(token))return;
        Session session=loggedInusersToken.get(token);
        loggedInUsers.remove(session);
        loggedInusersToken.remove(token);
    }
    public Optional<UserRole> getLoggedInUserDetails(Session session)
    {
        Optional<UserRole> user = Optional.ofNullable(loggedInUsers.get(session));
        return user;
    }
    public Session createSessionForUser(UserRole userRole) {
        Session session = new Session();
        Random random=new Random();

        session.setSessionId(java.util.UUID.randomUUID().toString());
        session.setToken(java.util.UUID.randomUUID().toString());
        session.setUser(userRole.getUser());
        session.setSessionStatus(SessionStatus_1.LOGGED_IN);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 14);
        session.setExpiringAt(cal.getTime());
        return session;
    }

    public List<UserRole> getallRegisteredUsers()
    {
        List<UserRole> users = new ArrayList<>(registeredUsers);
        return users;
    }
    public boolean validateSession(Session session)
    {
        if(loggedInUsers.containsKey(session)) {
            return true;
        }
        return false;
    }

    public UserRole CreateUserRole(LoginRequestDTO loginRequestDTO)
    {
        User user= new User();
        user.setUserName(loginRequestDTO.getUsername());
        user.setPassword(loginRequestDTO.getPassword());
        user.setUserName(loginRequestDTO.getUsername());
        user.setEmail(loginRequestDTO.getEmail());
        Random rand = new Random();
        user.setUserId(rand.nextInt(1000000007));

        Role role=getRolefortheRequestedLoginUser(loginRequestDTO);
        UserRole userRole=new UserRole();
        userRole.setUser(user);
        userRole.getRoles().add(role);

        return userRole;
    }
    private Role getRolefortheRequestedLoginUser(LoginRequestDTO loginRequestDTO)
    {
        Role role=new Role();
        if(loginRequestDTO.getUsername().equals("anup"))
        {
            role.setRoleType(RoleType.ADMIN);
            role.setRolename("Role1");
            role.setDescription("Admin role ");
        }
        else
        {
            role.setRoleType(RoleType.NORMAL);
            role.setRolename("Role2");
            role.setDescription("Role2");
        }
        return role;
    }
    public boolean ValidateToken(String token)
    {
        if(loggedInusersToken.containsKey(token))return true;
        return false;
    }

}
