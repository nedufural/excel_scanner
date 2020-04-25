var ss = SpreadsheetApp.openByUrl("https://docs.google.com/spreadsheets/d/1hrffHHOjf4dnX5yYPK9hLgl6MSM3cRGiTXZG7W3I-4U/edit#gid=0");

var sheet = ss.getSheetByName('Barcodes');


function doPost(e){
var action = e.parameter.action;

if(action == 'addCode'){
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