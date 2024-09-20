var viewModel = function () {
    var self = this;
    this.form = {
        userCode: ko.observable(),
        password: ko.observable(),
        remember: ko.observable(false),
        city: null
    };
    this.message = ko.observable();
    this.loginClick = function (form) {
        if(utils.isEmpty(self.form.password())) // ブラウザがパスワードを記憶している場合、Knockoutの監視を実行しない
            self.form.password($("#password").val());
        if(utils.isEmpty(self.form.userCode()))
            self.form.userCode($("#userCode").val());
        $.ajax({
            type: "POST",
            url: rootPath+"/sys/login.do",
            data: ko.toJSON(self.form),
            dataType: "json",
            contentType: "application/json",
            success: function (d) {
                if (d.status == 'success') {
                    self.message("ログインに成功しました。しばらくお待ちください...");
                    window.location.href = rootPath+'/';
                } else {
                    self.message(d.message);
                }
            },
            error: function (e) {
                self.message("ログインに失敗しました");
            },
            beforeSend: function () {
                $(form).find("input").attr("disabled", true);
                self.message("ログイン処理中です。しばらくお待ちください...");
            },
            complete: function () {
                $(form).find("input").attr("disabled", false);
            }
        });
    };

    this.resetClick = function () {
        self.form.userCode("");
        self.form.password("");
        self.form.remember(false);
    };

    this.init = function () {
        $.getJSON("http://api.map.baidu.com/location/ip?ak=F454f8a5efe5e577997931cc01de3974&callback=?", function (d) {
            self.form.city = d.content.address;
        });
        if (top != window) top.window.location = window.location;
        // 以前にクッキーが設定されていたかどうかを確認し、設定されていれば【覚えておく】チェックボックスを設定
        if(com.cookie('userCode') != null){
            self.form.remember(true);
        } else {
            self.form.remember(false);
        }

        // クッキーを読み込む
        if(self.form.remember()){
            self.form.userCode(com.cookie('userCode'));
            self.form.password(com.cookie('password'));
        }
    };

    this.init();
};
