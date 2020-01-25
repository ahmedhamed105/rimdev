
export interface IDevice {
    devicename: String,
    deviceinfo: String,
    deviceip : String,
    devicemac : String,
    deviceosversion : String,
    deviceosunknow : String,
    devicetokean : String,
    devicelong : number,
    devicelatitude : number,
    deviceOSID :  {
        id: number,
        deviceOS: String
    },
    devicetypeID : {
        id: number,
        devtype: String
    },
    devicebrowser: String,
    deviceBVersion : String,
    isMobile: boolean,
    isDesktopDevice : boolean,
    isTablet : boolean
}

