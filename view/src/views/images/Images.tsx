import {Table, Space, Button, Popconfirm, message} from 'antd';
import {DeleteOutlined, EyeOutlined} from '@ant-design/icons';
import {useState} from 'react';

interface DockerImage {
    id: string;
    repo: string;
    tag: string;
    size: string;
    created: string;
}

const initialImages: DockerImage[] = [
    {
        id: '1',
        repo: 'redis',
        tag: '7.0',
        size: '27MB',
        created: '2024-04-30 14:00',
    },
];

export const Images = () => {
    const [data, setData] = useState(initialImages);

    const handleDelete = (id: string) => {
        setData(data.filter(img => img.id !== id));
        message.success('镜像已删除（模拟）');
    };

    const columns = [
        {title: '仓库', dataIndex: 'repo'},
        {title: '标签', dataIndex: 'tag'},
        {title: '镜像大小', dataIndex: 'size'},
        {title: '创建时间', dataIndex: 'created'},
        {
            title: '操作',
            render: (_: any, record: DockerImage) => (
                <Space>
                    <Button icon={<EyeOutlined/>}/>
                    <Popconfirm title="确认删除该镜像？" onConfirm={() => handleDelete(record.id)}>
                        <Button icon={<DeleteOutlined/>} danger/>
                    </Popconfirm>
                </Space>
            ),
        },
    ];

    return <Table rowKey="id" columns={columns} dataSource={data} pagination={{pageSize: 5}}/>;
}
