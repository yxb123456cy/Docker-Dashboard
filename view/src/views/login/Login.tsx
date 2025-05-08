import {useEffect, useState} from "react";
import './css/login.css'
import {Button, Card, Checkbox, Form, Input, message} from "antd";
import {LockOutlined, UserOutlined} from "@ant-design/icons";


export const Login = () => {
    useEffect(() => {
        console.log("this is Login.tsx!");
    }, []);
    const [loading, setLoading] = useState(false);

    const onFinish = async (values: any) => {
        setLoading(true);
        try {
            // TODO: 调用实际登录 API
            const {username, password} = values;
            if (username === 'admin' && password === '123456') {
                message.success('登录成功');
                // TODO: 跳转到首页
            } else {
                message.error('账号或密码错误');
            }
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="login-container">
            <Card title="轻叶 Docker-DashAboard登录" className="login-card">
                <Form onFinish={onFinish} layout="vertical">
                    <Form.Item
                        name="username"
                        label="用户名"
                        rules={[{required: true, message: '请输入用户名'}]}
                    >
                        <Input prefix={<UserOutlined/>} placeholder="请输入用户名"/>
                    </Form.Item>

                    <Form.Item
                        name="password"
                        label="密码"
                        rules={[{required: true, message: '请输入密码'}]}
                    >
                        <Input.Password prefix={<LockOutlined/>} placeholder="请输入密码"/>
                    </Form.Item>
                    <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: 12 }}>
                        <Form.Item name="remember" valuePropName="checked" noStyle>
                            <Checkbox>保持登录</Checkbox>
                        </Form.Item>
                        <a href="/forgot-password">忘记密码？</a>
                    </div>
                    <h4>登录即表示同意《轻叶-DockerDashAboard许可协议》否则请勿使用</h4>
                    <Form.Item>
                        <Button
                            type="primary"
                            htmlType="submit"
                            block
                            loading={loading}
                        >
                            登录
                        </Button>
                    </Form.Item>
                </Form>
            </Card>
        </div>
    );
}