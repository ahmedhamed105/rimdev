
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
    mobile: boolean,
    desktopDevice : boolean,
    tablet : boolean,
    page:number
}

