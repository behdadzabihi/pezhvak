# pezhvak
I implemented this project based on the document you sent. The project has 2 entities named user and comment, both of which have 2 roles, admin and user, and admin and user can be insert and update,but only the role Admin can delete.The following technologies are also used in this project.

#java8

#spring boot 2.7.3

#spring data

#mapstruct

#lombok

#postgres

#keycloak

Also, to implement authentication and authorization, use keycloak and how to set it up is as follows:
1- download keycloak 
2-go to bin folder in keycloak:
in cmd  type :
kc.bat start-dev
and test it in postman with admin and user roles.
