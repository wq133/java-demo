package com.base.devdemo.completableFuture;


import com.base.devdemo.entity.Role;
import com.base.devdemo.entity.Student;


/**
 * 模拟一个rpc response<T>
 *           直接传入实例对象
 * 反射获取一个T的实例对象——不安全
 *
 * @param <T>
 */
public class RpcResponse<T> {
          public T pojo;

//          RpcResponse(Class<T> clazz) {
//                    try {
//                              this.pojo = clazz.getDeclaredConstructor().newInstance();
//                    } catch (Exception e) {
//                              throw new RuntimeException(e);
//                    }
//          }

          public RpcResponse(){
                    this.pojo = (T) new Student("1","ccc",23, Role.normal);
          }

          public RpcResponse(T pojo) {
                    this.pojo = pojo;
          }

          @Override
          public String toString(){
                    return pojo.toString();
          }

}
