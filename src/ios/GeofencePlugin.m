#import <Cordova/CDV.h>
#import "GeofencePlugin.h"

@implementation GeofencePlugin

- (void)inGeofence:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult;

    @try {
        NSDictionary *options = (NSDictionary*)[command.arguments objectAtIndex:0];
        
        float radius = [options[@"radius"] floatValue];

        NSDictionary *sourceLoc = (NSDictionary*)options[@"source"];
        NSDictionary *destinationLoc = (NSDictionary*)options[@"destination"];

        CLLocation *sLoc = [[CLLocation alloc] initWithLatitude:[sourceLoc[@"latitude"] floatValue] longitude:[sourceLoc[@"longitude"] floatValue]];
        CLLocation *dLoc = [[CLLocation alloc] initWithLatitude:[destinationLoc[@"latitude"] floatValue] longitude:[destinationLoc[@"longitude"] floatValue]];
        CLLocationDistance dist = [sLoc distanceFromLocation:dLoc];

        int inGeofence = 0;
        if(dist < radius){
            inGeofence = 1;
        }
        else{
            inGeofence = 0;
        }

        NSDictionary *result = @{
            @"distance": [NSNumber numberWithDouble:dist],
            @"radius": [NSNumber numberWithDouble:radius],
            @"inGeofence": [NSNumber numberWithInt:inGeofence]
        };
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:result];
    }
    @catch(NSException *ex){
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:ex.description];
    }

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)getDistance:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult;

    @try {
        NSDictionary *options = (NSDictionary*)[command.arguments objectAtIndex:0];

        NSDictionary *sourceLoc = (NSDictionary*)options[@"source"];
        NSDictionary *destinationLoc = (NSDictionary*)options[@"destination"];

        CLLocation *sLoc = [[CLLocation alloc] initWithLatitude:[sourceLoc[@"latitude"] floatValue] longitude:[sourceLoc[@"longitude"] floatValue]];
        CLLocation *dLoc = [[CLLocation alloc] initWithLatitude:[destinationLoc[@"latitude"] floatValue] longitude:[destinationLoc[@"longitude"] floatValue]];
        CLLocationDistance dist = [sLoc distanceFromLocation:dLoc];

        NSDictionary *result = @{
            @"distance": [NSNumber numberWithDouble:dist]
        };

        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:result];
    }
    @catch(NSException *ex){
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:ex.description];
    }

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
