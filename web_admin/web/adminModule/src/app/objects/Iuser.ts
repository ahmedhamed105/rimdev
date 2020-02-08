export interface Iuser {
    id: number,
    firstName: String,
    middleName: String,
    lastname : String,
    birthdate : Date,
    useridnumber : String,
    passportnumber : String,
    iDnumber : String,
    usertypeID: {
        id: number,
        usertype : String,
        publishnot : number
    }
  }