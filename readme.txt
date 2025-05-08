# 如何开启Docker远程访问?
解决方案:https://blog.csdn.net/sg_knight/article/details/126319965

// React快速函数组件模板;
import {useEffect, useState} from "react";
export const App = () => {
    const [data, setData] = useState("React")
    useEffect(() => {
        console.log("hello,React");
    }, []);
    return (
        <>
            <h1>学习{data}</h1>
            <button onClick={() => {
                setData("Vue")
            }}>
                点击我学习Vue;
            </button>
        </>
    )
}