/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


'use strict';
var ApplicationConfiguration = (function () {
    var applicationModuleName = 'reportsapp';
    var applicationModuleVendorDependencies = ['ngResource', 'ngAnimate', 'ngTouch', 'ui.router', 'ui.bootstrap'];
    var registerModule = function (moduleName) {
        angular
                .module(moduleName, []);
        angular
                .module(applicationModuleName)
                .requires
                .push(moduleName);
    };
    return {
        applicationModuleName: applicationModuleName,
        applicationModuleVendorDependencies: applicationModuleVendorDependencies,
        registerModule: registerModule
    };
})();
'use strict';
angular
        .module(ApplicationConfiguration.applicationModuleName, ApplicationConfiguration.applicationModuleVendorDependencies);
angular
        .module(ApplicationConfiguration.applicationModuleName)
        .config(['$locationProvider',
            function ($locationProvider) {
                $locationProvider.hashPrefix('!');
            }
        ]);
angular
        .element(document)
        .ready(function () {
            if (window.location.hash === '#_=_') {
                window.location.hash = '#';
            }
            angular
                    .bootstrap(document,
                            [ApplicationConfiguration.applicationModuleName]);
        });
'use strict';
ApplicationConfiguration.registerModule('reports');

'use strict';
angular
        .module('reports')
        .config(['$stateProvider',
            '$urlRouterProvider',
            function ($stateProvider, $urlRouterProvider) {
                $urlRouterProvider.otherwise('/Dashboard');
                $stateProvider
                        .state('Dashboard', {
                            url: '/Dashboard',
                            templateUrl: 'reports/modules/views/admin.html',
                            controller: 'reportsController'
                        })
                        .state('PaidAccounts', {
                            url: "/PaidAccounts",
                            templateUrl: "reports/modules/views/admin.html",
                            controller: 'reportsController'
                        })
                        .state('TrialAccounts', {
                            url: "/TrialAccounts",
                            templateUrl: "reports/modules/views/admin.html",
                            controller: 'reportsController'
                        })
                        .state('CustomersInformation', {
                            url: "/CustomersInformation",
                            templateUrl: "reports/modules/views/admin.html",
                            controller: 'reportsController'
                        });
            }
        ]);


'use strict';
angular
        .module('reports').directive('resize', function ($window) {
    return function ($scope, element, attr) {

        var w = angular.element($window);
        $scope.$watch(function () {
            return {
                'h': w.height(),
                'w': w.width()
            };
        }, function (newValue, oldValue) {
            $scope.iframeHeight = newValue.h - 115;
        }, true);
        w.bind('resize', function () {
            $scope.$apply();
        });
    }
});


'use strict';
angular
        .module('reports')
        .controller('reportsController', ['$scope', '$state', '$sce',
            function ($scope, $state, $sce) {
                $scope.currentreportUrl = $sce.trustAsResourceUrl(adminDetails.baseurl+'/rdPage.aspx?rdReport=' + $state.current.name + '&rdSecureKey=' + adminDetails.securityKey);
                $scope.currentreportTab = $state.current.name;
                $scope.iframeHeight = window.innerHeight - 115;
                $scope.adminDetails = adminDetails;
                $scope.checkSessionTimeOut = function () {
                    var date = new Date();
                    if (!localStorage.getItem("adminSessionTimeout")) {
                        return true;
                    }
                    else {
                        return (date.getTime() - localStorage.getItem("adminSessionTimeout") < 3540000)
                    }
                };

                $scope.reportLink = [{
                        title: 'Dashboard',
                        url: 'Dashboard',
                        controller:'reportsController'
                    }, {
                        title: 'Paid Accounts',
                        url: 'PaidAccounts'
                    }, {
                        title: 'Trial Accounts',
                        url: 'TrialAccounts'
                    }, {
                        title: 'Customer Info',
                        url: 'CustomersInformation'
                    }];

                $scope.setReportLink = function (activeTab) {
                    if ($scope.checkSessionTimeOut()) {
                        var date = new Date();
                        localStorage.setItem("adminSessionTimeout", date.getTime());
                        $scope.currentreportTab = activeTab.url;
                        $scope.currentreportUrl = $sce.trustAsResourceUrl(adminDetails.baseurl+'/rdPage.aspx?rdReport=' + activeTab.url + '&rdSecureKey=' + adminDetails.securityKey);
                    }
                    else {
                        setTimeout(function () {
                            $scope.formSubmit();
                        }, 100);
                    }
                };  
                $scope.isActiveReportTab = function (activeTab) {
                    if(!$scope.currentreportTab){
                        $scope.currentreportTab=$state.current.name;
                    }
                    return $scope.currentreportTab === activeTab.url;
                };
                $scope.formSubmit = function () {
                    document.getElementById("logoutForm").submit();
                };

            }
        ]);
        