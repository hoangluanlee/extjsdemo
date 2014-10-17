Ext.onReady(function() {
	Ext.QuickTips.init();
	var scoreId = Ext.getDom('scoreId').value;
	var subjectStore = new Ext.data.JsonStore({
		url : '../subject/view.action',
		root : 'data',
		totalProperty : 'total',
		fields : [ {
			name : 'id'
		}, {
			name : 'name'
		} ]
	});

	var courseStore = new Ext.data.JsonStore({
		url : '../course/view.action',
		root : 'data',
		totalProperty : 'total',
		fields : [ {
			name : 'id'
		}, {
			name : 'name'
		} ]
	});

	var mf = new Ext.FormPanel({
		url : addUrl,
		title : '',
		monitorValid : true,
		renderTo : Ext.getBody(),
		frame : true,
		cls : 'my-form-class',
		width : 350,
		baseCls : 'x-plain',
		labelWidth : 130,

		keys : [ {
			key : [ 10, 13 ],
			fn : function() {
				if (mf.getForm().isValid()) {
					mf.buttons[0].fireEvent('click');
				}
			}
		} ],
		items : [ {
			xtype : 'hidden',
			name : "id",
			value : scoreId
		}, {
			mode : 'remote',
			hiddenName : 'subjectId',
			fieldLabel : 'Subject',
			xtype : 'combo',
			minChars : 1,
			triggerAction : 'all',
			displayField : 'name',
			valueField : 'id',
			store : subjectStore,
			typeAhead : true
		}, {
			fieldLabel : 'Course',
			xtype : 'combo',
			typeAhead : true,
			mode : 'remote',
			minChars : 1,
			hiddenName : 'courseId',
			triggerAction : 'all',
			store : courseStore,
			displayField : 'name',
			valueField : 'id'
		}, {
			xtype : 'textfield',
			fieldLabel : 'score',
			name : 'score',
			allowBlank : false,
			emptyText : 'Student score...'
		} ],
		buttons : [ {
			id : 'mf.btn.save',
			text : 'Save',
			formBind : true,
			disabled : false,
			handler : function() {
				fnUpdateForm(mf);
			}
		}, {
			id : 'mf.btn.reset',
			text : 'Reset',
			disabled : true,
			handler : function() {
				fnResetForm(mf);
			}
		} ]
	});

	courseStore.load({
		callback : function() {
			subjectStore.load({
				callback : function() {
					if (!Ext.isEmpty(scoreId)) {
						mf.load({
							url : '../score/load.action',
							params : {
								'scoreId' : scoreId
							},
							failure : function(form, action) {
								Ext.Msg.alert("Load failed",
										action.result.errorMessage);
							},
							success : function(form, action) {
								mf.setTitle(action.result.data['name']);
							}
						});
					}
				}
			});
		}
	});

});

function fnLoadForm(theForm) {
	// for the purpose of this tutorial, load 1 record.
	theForm.getForm().load({
		url : loadUrl,
		headers : {
			Accept : 'application/json, text/javascript, */*; q=0.01'
		},
		waitMsg : 'loading...',
		params : {
			id : 1
		},
		success : function(form, action) {
			Ext.getCmp('mf.btn.save').setDisabled(true);
			Ext.getCmp('mf.btn.reset').setDisabled(true);
			Ext.getCmp('mf.btn.load').setDisabled(true);
		},
		failure : function(form, action) {
			Ext.Msg.alert('Warning', 'Error Unable to Load Form Data.'); // action.result.errorMessage
		}
	});
} // end fnLoadForm
function fnUpdateForm(theForm) {
	theForm.getForm().submit({
		success : function(form, action) {
			Ext.Msg.alert('Success', 'Data is stored in session.');
			form.reset();
		},
		failure : function(form, action) {
			Ext.Msg.alert('Warning', action.result.errorMessage);
		}
	});
} // end fnUpdateForm

function fnResetForm(theForm) {
	theForm.getForm().reset();
	Ext.getCmp('mf.btn.save').setDisabled(true);
	Ext.getCmp('mf.btn.reset').setDisabled(true);
} // end fnResetForm
