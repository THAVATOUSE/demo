// login.js
function showLogin() {
    document.getElementById("login-form").classList.add("active");
    document.getElementById("register-form").classList.remove("active");
}

function showRegister() {
    document.getElementById("login-form").classList.remove("active");
    document.getElementById("register-form").classList.add("active");
}

function sendCode() {
    const email = document.querySelector("#register-form input[type='email']").value;
    if (email) {
        alert(`验证码已发送至 ${email}`);
        // 实际项目中可调用API发送邮件验证码
    } else {
        alert("请输入邮箱地址！");
    }
}

function register() {
    alert("注册成功！");
    // 可跳转至登录页
    window.location.href = "login.html";
}

// 设置角色标题
const urlParams = new URLSearchParams(window.location.search);
const role = urlParams.get('role');
const titleMap = {
    farmer: "农户登录",
    consumer: "消费者登录",
    drone: "无人机管理登录"
};
document.getElementById("role-title").innerText = titleMap[role] || "登录";