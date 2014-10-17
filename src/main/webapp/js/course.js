Ext.onReady(function() {
	Ext.BLANK_IMAGE_URL = '../images/s.gif';
	Ext.QuickTips.init();
	var studentGrid = buildStudentGrid();//defined in student-page.js
	var subjectGrid = buildSubjectGrid();
	var courseGrid = buildCourseGrid();
	
	var viewport = new Ext.Viewport({
		layout : "border",
		id : 'movieview',
		renderTo : document.body,
		items : [ {
			region : "north",
			xtype : 'panel',
			html : '',
			height:30
		},  {
			region : 'center',
			xtype : 'panel',
			items : [ studentGrid],
			split : true,
			layout: 'fit'
		}, {
			region : 'east',
			xtype : 'tabpanel',
			activeTab : 0,			
			width : 400,
			collapseMode : 'mini',
			items : [subjectGrid, courseGrid]
		}, {
			region : 'south',
			xtype : 'panel',
			html : '',
			height:30
		} ]
	});

});
