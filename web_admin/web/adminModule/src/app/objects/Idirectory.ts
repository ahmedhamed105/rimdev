export interface Idirectory {
    parent: {
        id: number,
        pmenu: string,
        faIcon: string,
        hasChild: 1,
        parentLink : string,
        pagesID: {
            id: number,
            pagename: string,
            pagemenu: string
        }
    },
    child: {
        id: number,
        menuname: string,
        menulink: string,
        faIcon: string,
        pagesID: {
            id: number,
            pagename: string,
            pagemenu: string
        }
    }
}
