import {useEffect, useState} from "react";
import {Outlet, useLocation, useNavigate} from "react-router";
import {Avatar, Dropdown, Layout, Menu, message} from "antd";
import {
    AppstoreOutlined,
    ClusterOutlined,
    ContainerOutlined,
    DatabaseOutlined,
    DownOutlined,
    HomeOutlined, LogoutOutlined, MenuFoldOutlined, MenuUnfoldOutlined,
    SettingOutlined, UserOutlined
} from "@ant-design/icons";
import './css/DashboardLayout.css'

const {Header, Sider, Content, Footer} = Layout;

const items = [
    {label: '首页', key: '/Dashboard', icon: <HomeOutlined/>},
    {label: '容器管理', key: '/Dashboard/containers', icon: <ContainerOutlined/>},
    {label: '镜像管理', key: '/Dashboard/images', icon: <AppstoreOutlined/>},
    {label: '存储卷管理', key: '/Dashboard/volumes', icon: <DatabaseOutlined/>},
    {label: '网络管理', key: '/Dashboard/networks', icon: <ClusterOutlined/>},
    {label: '系统设置', key: '/Dashboard/settings', icon: <SettingOutlined/>},
];
export const DashboardLayout = () => {
    useEffect(() => {
        console.log("hello,React");
    }, []);
    const navigate = useNavigate();
    const location = useLocation();
    const [collapsed, setCollapsed] = useState(false);

    const handleLogout = () => {
        // 这里可以清除 token 或登录信息
        localStorage.removeItem('user');
        message.success('已退出登录')
            .then(res => console.log(res))
        navigate('/login');
    };
    const userMenu = (
        <Menu
            items={[
                {
                    key: 'logout',
                    icon: <LogoutOutlined/>,
                    label: '退出登录',
                    onClick: handleLogout,
                },
            ]}
        />
    );
    return (
        <Layout style={{minHeight: '100vh'}}>
            <Header className="dashboard-header">
                <div className="logo">Docker UI</div>
                <div className="header-right">
                    <Dropdown overlay={userMenu}>
                        <div style={{display: 'flex', alignItems: 'center', cursor: 'pointer'}}>
                            <Avatar size="small" icon={<UserOutlined/>} style={{marginRight: 8}}/>
                            <span style={{color: '#fff'}}>admin</span>
                            <DownOutlined style={{marginLeft: 4, color: '#fff'}}/>
                        </div>
                    </Dropdown>
                </div>
            </Header>

            <Layout>
                <Sider
                    width={200}
                    className="dashboard-sider"
                    collapsible
                    collapsed={collapsed}
                    onCollapse={setCollapsed}
                    trigger={null}
                >
                    <div className="sider-trigger" onClick={() => setCollapsed(!collapsed)}>
                        {collapsed ? <MenuUnfoldOutlined/> : <MenuFoldOutlined/>}
                    </div>
                    <Menu
                        mode="inline"
                        selectedKeys={[location.pathname]}
                        style={{height: '100%', borderRight: 0}}
                        items={items}
                        onClick={(e) => navigate(e.key)}
                    />
                </Sider>

                <Layout style={{padding: '16px'}}>
                    <Content className="dashboard-content">
                        <Outlet/>
                    </Content>
                    <Footer style={{textAlign: 'center'}}>
                        Docker UI ©2025 Created by You
                    </Footer>
                </Layout>
            </Layout>
        </Layout>
    );
}