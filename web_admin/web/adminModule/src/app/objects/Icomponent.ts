      export interface Icomponent {
        comp :{

            id: number,
            seqNum :number,
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
            arrayObject: number,
            selectValue: String,
            selectDisplay: String,
            changemethod : String

        },
        input: {
            id: number,
            inputActions: String,
            inputtypeID: {
                id: number,
                itype: String
            }
        }
      }
    