var prefix=""//"/running";
new Vue({


    el: '#app',
    data: function () {
        return {

            circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
            squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
            sizeList: ["large", "medium", "small"],

            activeIndex: '1',
            activeIndex2: '1',

            tableData: [],
            search: '',

            dialogFormVisible: false,
            // form: {
            //     address: 'http://geomodeling.njnu.edu.cn/',
            //     email: '921485453@qq.com',
            //     dateRange: [new Date(2019, 9, 5, 0, 0), new Date(2019, 9, 5, 7, 30)],
            //     interval:'1h',
            // },
            form: {
                type: '',
                id: '',
                address: '',
                manager:'',
                email: '',
                interval: '',
            },
            formLabelWidth: '120px'
        }
    },
    methods: {

        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },


        handleTest(index, row) {
            console.log(index, row);
            $.ajax({
                //请求方式
                type: "POST",
                //请求地址
                url: prefix+"/test",
                //数据，json字符串
                data: {
                    id: row.id,
                },
                //请求成功
                success: (result) => {
                    this.getList();
                    alert(row.address+" 状态已更新。")
                    console.log(result);
                },
                //请求失败，包含具体的错误信息
                error: function (e) {
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });
        },
        handleEdit(index, row) {
            console.log(index, row);
            this.form.type = "PUT";
            this.form.id = row.id;
            this.form.manager=row.manager;
            this.form.address = row.address;
            this.form.email = row.email;
            this.form.interval = row.interval;
            this.dialogFormVisible = true;
        },
        handleDelete(index, row) {
            console.log(index, row);
            if(confirm("确认删除 "+row.address+" ?"))
            {

                $.ajax({
                    //请求方式
                    type: "DELETE",
                    //请求地址
                    url: prefix+"/websiteRecord",
                    //数据，json字符串
                    data: {
                        id: row.id,
                    },
                    //请求成功
                    success: (result) => {
                        this.getList();
                        console.log(result);
                    },
                    //请求失败，包含具体的错误信息
                    error: function (e) {
                        console.log(e.status);
                        console.log(e.responseText);
                    }
                });

            }
        },
        getList() {
            $.ajax({
                //请求方式
                type: "GET",
                //请求地址
                url: prefix+"/list",
                //请求成功
                success: (result) => {
                    console.log(result);
                    this.tableData = result.data;
                },
                //请求失败，包含具体的错误信息
                error: function (e) {
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });
        },
        handleAdd() {
            this.form.type = "POST";
            this.form.id = '';
            this.form.manager='';
            this.form.address = '';
            this.form.email = '';
            this.form.interval = '';
            this.dialogFormVisible = true
            alert("123")
        },
        submitForm() {

            $.ajax({
                //请求方式
                type: this.form.type,
                //请求的媒体类型
                contentType: "application/json;charset=UTF-8",
                //请求地址
                url: prefix+"/websiteRecord",
                //数据，json字符串
                data: JSON.stringify(this.form),
                //请求成功
                success: (result) => {
                    if(result.code==0){
                        this.getList();
                        console.log(result);
                        this.dialogFormVisible = false;
                    }
                    else{
                        alert(result.msg);
                    }

                },
                //请求失败，包含具体的错误信息
                error: function (e) {
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });
        }
    },
    mounted() {
        this.getList();
    }
})