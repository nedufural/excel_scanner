var ss = null;
var sheet = null;

//https://docs.google.com/spreadsheets/d/1hrffHHOjf4dnX5yYPK9hLgl6MSM3cRGiTXZG7W3I-4U/edit#gid=0

function addAddress(e){
 // get the parameters from the user side
 var address = e.parameter.address;
 var sheetName = e.parameter.name;
 ss = SpreadsheetApp.openByUrl(address);
 sheet = ss.getSheetByName(sheetName);
}

function doPost(e){
var action = e.parameter.action;
  // global variables assignment function
  addAddress(e);
if(action == 'addCode'){
  // parameters to insert
  return addItem(e);

   }

}

function addItem(e){

var id  =  sheet.getLastRow(); 
var barcode = e.parameter.barcodes;
var result = {"message": "success", "date": Date(),"id" : id, "barcode":barcode}
var myJSON = JSON.stringify(result);  
sheet.appendRow([id,barcode]);

return ContentService.createTextOutput(myJSON).setMimeType(ContentService.MimeType.JSON);
}


function doGet(e){

var action = e.parameter.action;

  if(action == 'getItems'){
    return getItems(e);

  }
  
  }


function getItems(e){
  
  var records={};
 
  var rows = sheet_2.getRange(2, 1, sheet.getLastRow() - 1,sheet.getLastColumn()).getValues();
      data = [];

  for (var r = 0, l = rows.length; r < l; r++) {
    var row     = rows[r],
        record  = {};
    record['Barcode'] = row[2];
    record['Product Name']=row[3];
    record['Description']=row[4];
    record['Volume']=row[5];
    data.push(record);
    
   }
  records.items = data;
  var result=JSON.stringify(records);
  return ContentService.createTextOutput(result).setMimeType(ContentService.MimeType.JSON);
}