export interface Imenu {

            parent: {
                id: number,
                pmenu: String,
                faIcon: String,
                hasChild: number,
                pagesID: {
                    id: number,
                    pagename: String,
                    pagemenu: String
                }
            },
            child: [
                {
                    id: number,
                    menuname: String,
                    menulink: String,
                    faIcon: String,
                    pagesID: {
                        id: number,
                        pagename: String,
                        pagemenu: String
                    }
                }
            ]
        }
   