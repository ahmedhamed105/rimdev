import { Component, OnInit, ViewChild } from '@angular/core';
import { DevicesService } from '../services/devices.service';
import { LocationServiceService } from '../services/location-service.service';
import { AgGridAngular } from 'ag-grid-angular';

@Component({
  selector: 'app-devices',
  templateUrl: './devices.component.html',
  styleUrls: ['./devices.component.css']
})

export class DevicesComponent implements OnInit {
  @ViewChild('agGrid',{static: true}) agGrid: AgGridAngular;
  

  public devices = [];
  public device ;
  public page_number:number = 4 ;

 
 rowData: any;
 rowDatapage:any;


columnDefs=[

  {
    headerName : "select row",
    sortable: true, 
    width : 100,
    filter: true,
    checkboxSelection: true
  },
  {
    headerName : "IP",
    field : "deviceip",
    sortable: true, 
    filter: true
  },
  {
    headerName : "Browser",
    field : "devicebrowser",
    sortable: true, 
    filter: true
  },
  {
    headerName : "Browser version",
    field : "deviceBVersion",
    sortable: true, 
    filter: true
  },
  {
    headerName : "operating system",
    field : "deviceOSID.deviceOS",
    sortable: true, 
    filter: true
  },
  {
    headerName : "Device type",
    field : "devicetypeID.devtype",
    sortable: true, 
    filter: true
    
  },
  {
    headerName : "mobile",
    field : "mobile",
    sortable: true, 
    filter: true
  },
  {
    headerName : "tablet",
    field : "tablet",
    sortable: true, 
    filter: true
  },
  {
    headerName : "Desktop",
    field : "desktopDevice",
    sortable: true, 
    filter: true
  },
 
];

columnDefspage=[
  {
    headerName : "page name",
    field : "page_name",
    sortable: true, 
    filter: true
  }, 
  {
    headerName : "Visit time",
    field : "page_Date",
    sortable: true, 
    filter: true
  },

];

  constructor(private locationService: LocationServiceService,private _DevicesService: DevicesService) { }

  ngOnInit() {

    
    this.locationService.all_info(this.page_number).then(res => {
      this.device =this.locationService.status;
      console.log(this.device.tokean);
    });


    this.rowData =  this._DevicesService.getall();

  }


  blocked() {
    const selectedNodes = this.agGrid.api.getSelectedNodes();
    const selectedData = selectedNodes.map( node => node.data );
    const selectedDataStringPresentation = selectedData.map( node =>
       {
      this._DevicesService.block(node).subscribe(
        data =>  {
          alert(node.deviceip + ' blocked');
        } 
        );
      });
    
    
  //  alert(`Selected nodes: ${selectedDataStringPresentation}`);
}


unblocked() {
  const selectedNodes = this.agGrid.api.getSelectedNodes();
  const selectedData = selectedNodes.map( node => node.data );
  const selectedDataStringPresentation = selectedData.map( node =>
     {
    this._DevicesService.unblock(node).subscribe(
      data =>  {
        alert(node.deviceip + ' release blocked');
      } 
      );
    });
  
  
//  alert(`Selected nodes: ${selectedDataStringPresentation}`);
}

getpages() {
  const selectedNodes = this.agGrid.api.getSelectedNodes();
  const selectedData = selectedNodes.map( node => node.data );
  const selectedDataStringPresentation = selectedData.map( node =>
     {
  
     this.rowDatapage= this._DevicesService.getpages(node.id);

    });
  
  
//  alert(`Selected nodes: ${selectedDataStringPresentation}`);
}


}
