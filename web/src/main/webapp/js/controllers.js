var hauntedHousesControllers = angular.module("hauntedHousesControllers", ['hauntedHousesServices']);

hauntedHousesControllers.controller('housesCtrl', function ($scope, $rootScope, houseFactory) {
    houseFactory.getAllHouses(
            function (response) {
                $scope.houses = response.data;
            },
            $rootScope.unsuccessfulResponse
            );
});

hauntedHousesControllers.controller('houseDetailCtrl', function ($scope, $routeParams, $rootScope, houseFactory) {
    houseFactory.getHouse(
            $routeParams.id,
            function (response) {
                $scope.house = response.data;
            },
            $rootScope.unsuccessfulResponse
            );
});

// unfinished, doesn't work:
// ---------------
hauntedHousesControllers.controller('newHouseCtrl',
    function ($scope, $routeParams, $http, $location, $rootScope, houseFactory) {        
        
        houseFactory.getAllPeople(
                function (response) {
                    $scope.persons = response.data;
                },
                $rootScope.unsuccessfulResponse
                );
        
        $scope.house = {
            'name': '',
            'address': '',
            'ownerId': ''
            
        };

        $scope.create = function (house) {
             $http({
                method: 'POST',
                url: '/pa165/rest/house/create',
                data: house
            }).then(function success(response) {
                //change view to list of products
                $location.path("/houses");
            }, function error(response) {
                $rootScope.unsuccessfulResponse;
            });
        };
   
});
// ---------------

hauntedHousesControllers.controller('ghostsCtrl', function ($scope, $rootScope, ghostFactory) {
    ghostFactory.getAllGhosts(
            function (response) {
                $scope.ghosts = response.data;
            },
            $rootScope.unsuccessfulResponse
            );
});

hauntedHousesControllers.controller('ghostDetailCtrl', function ($scope, $routeParams, $rootScope, ghostFactory) {
    ghostFactory.getGhost(
            $routeParams.id,
            function (response) {
                $scope.ghost = response.data;
            },
            $rootScope.unsuccessfulResponse
            );
});

/**
 * ABILITIES
 * ABILITIES
 * ABILITIES
 * ABILITIES
 * ABILITIES 
 */

hauntedHousesControllers.controller('abilitiesCtrl', function ($scope, $http, $location, $rootScope, abilityFactory) {
    abilityFactory.getAllAbilities(
            function (response) {
                $scope.abilities = response.data;
            },
            $rootScope.unsuccessfulResponse
            );
    $scope.deleteAbility = function (ability) {
        $http.delete('/pa165/rest/ability/delete/{id}'.replace("{id}", ability.id))
            .then(function success(response) {
                abilityFactory.getAllAbilities(
                    function (response) {
                        $scope.abilities = response.data;
                    },
                    $rootScope.unsuccessfulResponse
                    );
            }, function error(response) {
                $rootScope.unsuccessfulResponse;
            });
    };
    
});

hauntedHousesControllers.controller('abilityDetailCtrl', function ($scope, $routeParams, $rootScope, abilityFactory) {
    abilityFactory.getAbility(
            $routeParams.id,
            function (response) {
                $scope.ability = response.data;
            },
            $rootScope.unsuccessfulResponse
            );
});

hauntedHousesControllers.controller('newAbilityCtrl',
    function ($scope, $routeParams, $http, $location, $rootScope, abilityFactory) {

        abilityFactory.getAbilityTypes(
                function (response) {
                    $scope.types = response.data;
                },
                $rootScope.unsuccessfulResponse
                );
        
        $scope.toopes = ['NOISE', 'FIRE', 'DARKNESS', 'GORE'];
        
        $scope.ability = {
            'name': '',
            'description': '',
            'type': '' //this shit is causing some problems, not when creating the enum in controller
        };

        $scope.create = function (ability) {
             $http({
                method: 'POST',
                url: '/pa165/rest/ability/create',
                data: ability
            }).then(function success(response) {
                //change view to list of products
                $location.path("/abilities");
            }, function error(response) {
                $rootScope.unsuccessfulResponse;
            });
        };
   

});