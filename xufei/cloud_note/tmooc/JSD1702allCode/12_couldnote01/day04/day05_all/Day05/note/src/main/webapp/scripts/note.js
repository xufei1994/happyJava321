// scripts/note.js 编码一定是 utf-8

var SUCCESS = 0;
var ERROR = 1;

$(function(){
	
	//var userId = getCookie('userId');
	//console.log(userId);
	
	//网页加载以后, 立即读取笔记本列表
	loadNotebooks();
	//on() 方法绑定事件可以区别事件源
	//click() 方法绑定事件, 无法区别事件源
	//绑定笔记本列表区域的点击事件
	$('#notebook-list').on(
			'click','.notebook', loadNotes);
	
});

/** 笔记本项目点击事件处理方法, 加载全部笔记 */
function loadNotes(){
	var li = $(this);//当前被点击的对象li
	
	//在被点击的笔记本li增加选定效果
	li.parent().find('a').removeClass('checked');
	li.find('a').addClass('checked');
	
	var url = 'note/list.do';
	var data={notebookId:li.data('notebookId')};
	console.log(data);
	$.getJSON(url, data, function(result){
		if(result.state==SUCCESS){
			var notes = result.data;
			showNotes(notes);
		}else{
			alert(result.message);
		}
	});
}
/** 将笔记列表信息显示到屏幕上 */
function showNotes(notes){
	console.log(notes); 
	//将每个笔记对象显示到屏幕的ul区域
	var ul = $('#note-list ul');
	ul.empty();
	for(var i=0; i<notes.length; i++){
		var note = notes[i];
		var li = noteTemplate.replace(
				'[title]', note.title);
		li = $(li);
		ul.append(li);
	}
}

var noteTemplate = '<li class="online">'+
	'<a>'+
	'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> [title]<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>'+
	'</a>'+
	'<div class="note_menu" tabindex="-1">'+
	'<dl>'+
		'<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'+
		'<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'+
		'<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'+
	'</dl>'+
	'</div>'+
	'</li>';

/** 加载笔记本列表数据 */
function loadNotebooks(){
	//利用ajax从服务器获取(get)数据, 使用getJSON方法
	var url = 'notebook/list.do';
	var data = {userId:getCookie('userId'),
			name:'demo'};
	$.getJSON(url, data, function(result){
		console.log(result);
		if(result.state==SUCCESS){
			var notebooks = result.data;
			//在showNotebooks方法中将全部的
			//笔记本数据 notebooks 显示到 
			// notebook-list 区域
			showNotebooks(notebooks);
		}else{
			alert(result.message);
		}
	});
}
/** 在notebook-list区域中显示笔记本列表 */
function showNotebooks(notebooks){
	//找显示笔记本列表的区域的ul元素
	//遍历notebooks数组, 将为每个对象创建一个li
	//元素, 添加到 ul元素中.
	var ul = $('#notebook-list ul');
	ul.empty();
	for(var i=0; i<notebooks.length; i++){
		var notebook = notebooks[i];
		var li = notebookTemplate.replace(
				'[name]', notebook.name);
		li = $(li);
		//将 notebook.id 绑定到 li
		li.data('notebookId', notebook.id);
		
		ul.append(li);
	}
}
var notebookTemplate = 
	'<li class="online notebook">'+
	'<a><i class="fa fa-book" title="online" '+
	'rel="tooltip-bottom"></i> [name]</a>'+
	'</li>';










