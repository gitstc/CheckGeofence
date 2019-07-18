#import <Cordova/CDV.h>
#import <CoreLocation/CoreLocation.h>

@interface GeofencePlugin: CDVPlugin {
}

- (void)inGeofence:(CDVInvokedUrlCommand*)command;
- (void)getDistance:(CDVInvokedUrlCommand*)command;

@end
