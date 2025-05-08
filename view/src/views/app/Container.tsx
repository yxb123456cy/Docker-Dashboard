import {
    Table,
    Button,
    Space,
    Modal,
    Form,
    Input,
    Drawer,
    Tag,
    Popconfirm,
    message,
} from 'antd';
import {PlusOutlined, DeleteOutlined, EditOutlined, EyeOutlined} from '@ant-design/icons';
import {useState} from 'react';
import * as React from "react";

interface Container {
    id: string;
    name: string;
    image: string;
    state: string;
    ports: string;
    created: string;
    status: string;// 运行时长;

}

const initialData: Container[] = [
    {
        id: '1',
        name: 'redis-container',
        image: 'redis:7.0',
        state: 'running',
        ports: '6379->6379/tcp',
        created: '2024-05-01 10:23',
        status: "Up 6 minutes",
    },
];

export const Container = () => {
    const [data, setData] = useState(initialData);
    const [selectedRowKeys, setSelectedRowKeys] = useState<React.Key[]>([]);

    const [isCreateModalVisible, setCreateModalVisible] = useState(false);
    const [createForm] = Form.useForm();

    const [drawerVisible, setDrawerVisible] = useState(false);
    const [drawerData, setDrawerData] = useState<Container | null>(null);

    const [editVisible, setEditVisible] = useState(false);
    const [editForm] = Form.useForm();

    const handleCreate = (values: any) => {
        const newContainer: Container = {
            id: Date.now().toString(),
            ...values,
            status: 'created',
            ports: '6379->6379/tcp',
            created: new Date().toLocaleString(),
        };
        setData([...data, newContainer]);
        setCreateModalVisible(false);
        createForm.resetFields();
        message.success('容器创建成功（模拟）');
    };

    const handleDelete = () => {
        setData(data.filter((item) => !selectedRowKeys.includes(item.id)));
        setSelectedRowKeys([]);
        message.success('容器已删除（模拟）');
    };

    const handleEdit = (record: Container) => {
        setEditVisible(true);
        setDrawerData(record);
        editForm.setFieldsValue(record);
    };

    const submitEdit = (values: any) => {
        setData(data.map(item => item.id === drawerData?.id ? {...item, ...values} : item));
        setEditVisible(false);
        message.success('容器已更新（模拟）');
    };

    /**
     * 表格 列配置;
     */
    const columns = [
        {title: '容器名称', dataIndex: 'name', key: 'name'},
        {title: '镜像', dataIndex: 'image', key: 'image'},
        {
            title: '状态',
            dataIndex: 'state',
            key: 'state',
            render: (state: string) => (
                <Tag color={state === 'running' ? 'green' : 'orange'}>{state}</Tag>
            ),
        },
        {
            title: '运行时长',
            dataIndex: 'status',
            key: 'status',
            render: (status: string) => (
                <Tag color={'pink'}>{status}</Tag>
            ),
        },
        {title: '端口映射', dataIndex: 'ports', key: 'ports'},
        {title: '创建时间', dataIndex: 'created', key: 'created'},
        {
            title: '操作',
            key: 'actions',
            render: (_: any, record: Container) => (
                <Space>
                    <Button icon={<EyeOutlined/>} onClick={() => {
                        setDrawerVisible(true);
                        setDrawerData(record);
                    }}/>
                    <Button icon={<EditOutlined/>} onClick={() => handleEdit(record)}/>
                </Space>
            ),
        },
    ];

    return (
        <>
            <Space style={{marginBottom: 16}}>
                <Button type="primary" icon={<PlusOutlined/>} onClick={() => setCreateModalVisible(true)}>
                    新建容器
                </Button>
                <Popconfirm title="确定删除选中容器吗？" onConfirm={handleDelete}>
                    <Button danger icon={<DeleteOutlined/>} disabled={selectedRowKeys.length === 0}>
                        批量删除
                    </Button>
                </Popconfirm>
            </Space>

            {/*表格展示Table*/}
            <Table
                rowKey="id"
                rowSelection={{
                    selectedRowKeys,
                    onChange: setSelectedRowKeys,
                }}
                columns={columns}
                dataSource={data}
                pagination={{pageSize: 5}}
            />


            {/* 新建容器弹窗 */}
            <Modal
                open={isCreateModalVisible}
                title="新建容器"
                onCancel={() => setCreateModalVisible(false)}
                onOk={() => createForm.submit()}
            >
                <Form form={createForm} onFinish={handleCreate} layout="vertical">
                    <Form.Item name="name" label="容器名称" rules={[{required: true}]}>
                        <Input/>
                    </Form.Item>
                    <Form.Item name="image" label="镜像名称" rules={[{required: true}]}>
                        <Input placeholder="如 redis:7.0"/>
                    </Form.Item>
                </Form>
            </Modal>


            {/* 查看详情抽屉 */}
            <Drawer
                open={drawerVisible}
                title="容器详情"
                onClose={() => setDrawerVisible(false)}
                width={400}
            >
                {drawerData && (
                    <ul>
                        <li><strong>名称：</strong>{drawerData.name}</li>
                        <li><strong>所属镜像：</strong>{drawerData.image}</li>
                        <li><strong>状态：</strong>{drawerData.state}</li>
                        <li><strong>运行时长：</strong>{drawerData.status}</li>
                        <li><strong>端口：</strong>{drawerData.ports}</li>
                        <li><strong>创建时间：</strong>{drawerData.created}</li>
                    </ul>
                )}
            </Drawer>


            {/* 编辑容器抽屉 */}
            <Drawer
                open={editVisible}
                title="编辑容器"
                onClose={() => setEditVisible(false)}
                width={400}
            >
                <Form layout="vertical" form={editForm} onFinish={submitEdit}>
                    <Form.Item name="name" label="容器名称" rules={[{required: true}]}>
                        <Input/>
                    </Form.Item>
                    <Form.Item name="image" label="镜像名称" rules={[{required: true}]}>
                        <Input/>
                    </Form.Item>
                    <Form.Item>
                        <Button type="primary" htmlType="submit">保存修改</Button>
                    </Form.Item>
                </Form>
            </Drawer>
        </>
    );
}
