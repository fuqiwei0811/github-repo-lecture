// 新しいユーザーを追加
function newUsers(){
    // ユーザー追加ダイアログ
    $('#adddg').dialog('open').dialog('setTitle', 'ユーザーを追加');
    // データをクリア
    $('#fam').form('clear');
}

// ユーザー情報の編集
function editUsers(){
    // 行を選択
    var row = $('#datagrid').datagrid('getSelected');
    if (row){
        $('#modifydg').dialog('open').dialog('setTitle', '情報を変更');
        // 変更前のユーザー情報を表示
        $('#fim').form('load', row);
    }
}

// 情報保存ボタンのイベント
function saveUsers(){
    var row = $('#datagrid').datagrid('getSelected');
    var add;
    if(row == null){
        add = "/user/update?id=0";
    } else {
        add = "/user/update?id=" + row.userId;
    }

    $('#fim').form('submit', {
        url: add,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('(' + result + ')');
            if (result.success){
                $('#modifydg').dialog('close');		// ダイアログを閉じる
                $('#datagrid').datagrid('reload');	// ユーザーデータを再読み込み
                $.messager.show({
                    title: '成功',
                    msg: '保存が成功しました'
                });
            } else {
                $.messager.show({
                    title: 'エラー',
                    msg: result.msg
                });
            }
        }
    });
}

// ユーザー情報追加ボタンのイベント
function addUsers(){
    var add = "/user/save_users";
    $('#fam').form('submit', {
        url: add,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('(' + result + ')');
            if (result.success){
                $('#adddg').dialog('close');		// ダイアログを閉じる
                $('#datagrid').datagrid('reload');	// ユーザーデータを再読み込み
                $.messager.show({
                    title: '成功',
                    msg: '追加が成功しました'
                });
            } else {
                $.messager.show({
                    title: 'エラー',
                    msg: result.msg
                });
            }
        }
    });
}

// ユーザー削除ボタンのイベント
function removeUsers(){
    var row = $('#datagrid').datagrid('getSelected');

    if (row){
        $.messager.confirm('確認', 'このユーザーを削除してもよろしいですか?', function(r){
            if (r){
                $.post('/user/remove_users', {id: row.userId}, function(result){
                    if (result.success){
                        $('#datagrid').datagrid('reload');	// ユーザーデータを再読み込み
                        $.messager.show({
                            title: '成功',
                            msg: '削除が成功しました'
                        });
                    } else {
                        $.messager.show({	// エラーメッセージを表示
                            title: 'エラー',
                            msg: result.msg
                        });
                    }
                }, 'json');
            }
        });
    }
}
