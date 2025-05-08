import {Table, Button, Popconfirm,  Tag, message} from 'antd';
import {DeleteOutlined} from '@ant-design/icons';
import {useState} from 'react';

interface DockerNetwork {
    id: string;
    name: string;
    driver: string;
    scope: string;
}

const initialNetworks: DockerNetwork[] = [
    {id: '1', name: 'bridge', driver: 'bridge', scope: 'local'},
    {id: '2', name: 'host', driver: 'host', scope: 'local'},
];

export const Network = () => {
    const [data, setData] = useState(initialNetworks);

    const handleDelete = (id: string) => {
        setData(data.filter(n => n.id !== id));
        message.success('网络已删除（模拟）');
    };

    const columns = [
        {title: '名称', dataIndex: 'name'},
        {
            title: '驱动',
            dataIndex: 'driver',
            render: (val: string) => <Tag>{val}</Tag>,
        },
        {title: '作用域', dataIndex: 'scope'},
        {
            title: '操作',
            render: (_: any, record: DockerNetwork) => (
                <Popconfirm title="确认删除该网络？" onConfirm={() => handleDelete(record.id)}>
                    <Button danger icon={<DeleteOutlined/>}/>
                </Popconfirm>
            ),
        },
    ];

    return <Table rowKey="id" columns={columns} dataSource={data}/>;
}
