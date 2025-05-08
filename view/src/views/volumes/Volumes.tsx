import {Table, Button, Popconfirm, Space, message} from 'antd';
import {DeleteOutlined, EyeOutlined} from '@ant-design/icons';
import {useState} from 'react';

interface DockerVolume {
    name: string;
    driver: string;
    mountpoint: string;
    created: string;
}

const initialVolumes: DockerVolume[] = [
    {
        name: 'redis-data',
        driver: 'local',
        mountpoint: '/var/lib/docker/volumes/redis-data/_data',
        created: '2024-04-29 11:00',
    },
];

export const Volumes = () => {
    const [data, setData] = useState(initialVolumes);

    const handleDelete = (name: string) => {
        setData(data.filter(v => v.name !== name));
        message.success('卷已删除（模拟）');
    };

    const columns = [
        {title: '名称', dataIndex: 'name'},
        {title: '驱动', dataIndex: 'driver'},
        {title: '挂载路径', dataIndex: 'mountpoint'},
        {title: '创建时间', dataIndex: 'created'},
        {
            title: '操作',
            render: (_: any, record: DockerVolume) => (
                <Space>
                    <Button icon={<EyeOutlined/>}/>
                    <Popconfirm title="确认删除该卷？" onConfirm={() => handleDelete(record.name)}>
                        <Button danger icon={<DeleteOutlined/>}/>
                    </Popconfirm>
                </Space>
            ),
        },
    ];

    return <Table rowKey="name" columns={columns} dataSource={data}/>;
}
