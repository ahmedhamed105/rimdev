      export interface Icomponent {
        comp :{

            id: number,
            name: String,
            groupname: String,
            labelname : String,
            formname: String,
            ccode: String,
            ctype : String,
            crequired: number,
            cpattern: number,
            patterndesgin : String
        },
        select : {
            id: number,
            arrayName: String,
            arrayObject: String,
            selectValue: String,
            selectDisplay: String

        }  
      }
      