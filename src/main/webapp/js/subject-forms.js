Ext.onReady(function(){
	Ext.QuickTips.init();
	
	var mf = new Ext.FormPanel({
			url: addUrl,
			title: 'tttt',
			monitorValid:true,
			renderTo: Ext.getBody(),
			frame: true,
			cls: 'my-form-class',
			width: 350,
			items: [{
					xtype: 'textfield',
					fieldLabel: 'Subject name:',
					name: 'name',
					allowBlank : false
			},{
					xtype: 'textfield',
					fieldLabel: 'Description',
					name: 'description'
			}],
			buttons: [{
					id: 'mf.btn.load',
					text: 'Load',
					handler: function() {
						fnLoadForm(mf);
					}
				},{
					id: 'mf.btn.test',
					text: 'Test',
					disabled: false,
					handler: function() {
						fnTestForm(mf);
					}
			},
			{
				id: 'mf.btn.save',
				text: 'Save',
				formBind:true,
				disabled: false,
				handler: function() {
					fnUpdateForm(mf);
				}
		},
			{
					id: 'mf.btn.reset',
					text: 'Reset',
					disabled: true,
					handler: function() {
						fnResetForm(mf);
					}
			}]
	});
	mf.load({
	    url: '../subject/load.action',
	    params: {
	        'id': 12
	    },
	    failure: function(form, action) {
	        Ext.Msg.alert("Load failed", action.result.errorMessage);
	    },
	    success: function(form, action){
	    	mf.setTitle(action.result.data['name']);
	    }
	});
});


function fnLoadForm(theForm) 
{
	//for the purpose of this tutorial, load 1 record.
	theForm.getForm().load({
		url: loadUrl,
		headers: {Accept: 'application/json, text/javascript, */*; q=0.01'},
        waitMsg: 'loading...',
		params : {
			id: 1
		},
		success: function(form, action) {
			Ext.getCmp('mf.btn.save').setDisabled(true);		
			Ext.getCmp('mf.btn.reset').setDisabled(true);		
			Ext.getCmp('mf.btn.load').setDisabled(true);		
		},
		failure: function(form, action) {
			Ext.Msg.alert('Warning', 'Error Unable to Load Form Data.'); //action.result.errorMessage
		}
	});
} //end fnLoadForm
function fnUpdateForm(theForm)
{
	theForm.getForm().submit({
		success: function(form, action) {
			Ext.Msg.alert('Success', 'Data is stored in session.');
			form.reset();
		},
		failure: function(form, action) {
			Ext.Msg.alert('Warning', action.result.errorMessage); 
		}
	});
} //end fnUpdateForm


function fnResetForm(theForm)
{
	theForm.getForm().reset();
	Ext.getCmp('mf.btn.save').setDisabled(true);		
	Ext.getCmp('mf.btn.reset').setDisabled(true);		
} //end fnResetForm