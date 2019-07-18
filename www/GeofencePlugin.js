var GeofencePlugin = {
    inGeofence: function(successCB,errorCB,options){
        cordova.exec(function(res){
            successCB ? successCB(res) : false;
        },errorCB,"GeofencePlugin","inGeofence",[options]);
    },
    getDistance: function(successCB,errorCB,options){
        cordova.exec(function(res){
            successCB ? successCB(res) : false;
        },errorCB,"GeofencePlugin","getDistance",[options]);
    }
};

module.exports = GeofencePlugin;