/**
 * toString()方法的使用与重写
 * equals()方法的使用与重写
 * 在使用"=="判断相等时，若等号两边是基本数据类型则判断值是否相等
 * 如果是引用类型则判断对象是否是在同一地址
 * equals()方法的思想既是 人 对象下的身份证号唯一，
 * 既若是身份证号相同可以认为是同一个人
 */

public class java_34 {
    public static void main(String[] args) {
        Student1 a = new Student1(1001L, 18, "张三");
        System.out.println(a.toString());// 默认返回值为"类名@地址",可以通过重载使其返回更有用的值

        Person1 b = new Person1("李四", 18, 175, 1003);
        System.out.println(b.toString());// 对toString()方法进行了重写

        Student1 c = new Student1(1001L, 18, "张三");
        System.out.println(c.toString());// 从这步可知a与c的地址不同
        System.out.println(c.equals(a));// 地址不同因此返回值为false，表示两个不相等//不进行重写默认比较地址

        Person1 d = new Person1("赵六", 19, 174, 1003);
        System.out.println(d.equals(b));// 通过重写equals方案，使得按照需求对两个类进行比较

    }
}

class Student1 {

    long id;
    int age;
    String name;

    Student1(long id, int age, String name) {

        this.id = id;
        this.age = age;
        this.name = name;
    }
}

class Person1 {

    String name;
    int age;
    int height;
    int id;

    Person1(String name, int age, int height, int id) {

        this.name = name;
        this.age = age;
        this.height = height;
    }

    @Override
    public String toString() {// 在该类里对toString()进行重写

        return "名字：" + name + "\n" + "年龄：" + age + "\n" + "身高：" + height;
    }

    // public String equals() {// 这里对equals()方法进行重写

    // }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {

            return true;// 同一个对象
        }

        if (obj == null || this.getClass() != obj.getClass()) {

            return false;
        }

        Person1 that = (Person1) obj;
        return (this.id == that.id);// 这里的that可以是其他任何变量，相当于此处新申请了一个变量that
    }

}