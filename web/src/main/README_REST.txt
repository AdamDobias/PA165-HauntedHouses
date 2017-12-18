------------------------------------------------------------------------------------------------------------------
Haunted Houses: REST
------------------------------------------------------------------------------------------------------------------

PA165, FI MUNI, 2017

---------------------------------------------
GENERAL INFORMATION
---------------------------------------------

API root URL: http://localhost:8080/pa165/rest

Roles: Admin, Owner, Resident

---------------------------------------------
COMMANDS
---------------------------------------------

The following commands do not require to be logged in:

Find all houses:     curl -X GET http://localhost:8080/pa165/rest/house
Find all ghosts:     curl -X GET http://localhost:8080/pa165/rest/ghost
Find all abilities:  curl -X GET http://localhost:8080/pa165/rest/ability
Find anonymous user: curl -X GET http://localhost:8080/pa165/rest/person

To find a concrete house, ghost, or ability, add the ID parameter, for example:
curl -X GET http://localhost:8080/pa165/rest/ghost/2

---------------------------------------------

The following commands require to be logged in:

Find the logged user: curl -X GET http://localhost:8080/pa165/rest/person
