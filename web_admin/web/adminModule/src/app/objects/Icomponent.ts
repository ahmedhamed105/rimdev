      export interface Icomponent {
        parent: {
            id: number,
            pcodeTittle: String,
            parentPostion: number,
            parentType: String,
            parentName: String
        },
      child : [{
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
            changemethod : String,
            webService : String

        },
        input: {
            id: number,
            inputActions: String,
            inputtypeID: {
                id: number,
                itype: String
            }
        },
        button: {
            id: number,
            buttonClass: String,
            buttonMethod: String,
            buttonType: String,
            buttonService: String
        }

      }]  
       
      }
    