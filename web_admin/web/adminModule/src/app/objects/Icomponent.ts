      export interface Icomponent {
        pagename: string,
        menuname: string,
        parentname: string
        ,parent: {
            id: number,
            pcodeTittle: string,
            parentPostion: number,
            parentType: string,
            parentName: string,
            firstmethod:string
        },
      child : [{
        comp :{

            id: number,
            seqNum :number,
            name: string,
            groupname: string,
            labelname : string,
            formname: string,
            ccode: string,
            ctype : string,
            crequired: number,
            cpattern: number,
            patterndesgin : string
        },
        select : {
            id: number,
            arrayName: string,
            arrayObject: number,
            selectValue: string,
            selectDisplay: string,
            changemethod : string,
            webService : string

        },
        input: {
            id: number,
            inputActions: string,
            inputtypeID: {
                id: number,
                itype: string
            }
        },
        button: {
            id: number,
            buttonClass: string,
            buttonMethod: string,
            buttonType: string,
            buttonService: string
        }

      }]  
       
      }
    