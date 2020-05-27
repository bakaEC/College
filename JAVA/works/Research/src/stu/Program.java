package stu;

public class Program
{
    static void Main(String[] args)
    {
        String str = "Hello,C#!!!";
        //调用。
        String result = StringToHex16String(str);
        Console.WriteLine(String.Format("将普通字符串:{0}转换成16进制字符串是:{1}", str, result));
        Console.ReadKey();
    }    
    /// <summary>
    /// 此方法用于将普通字符串转换成16进制的字符串。
    /// </summary>
    /// <param name="_str">要转换的字符串。</param>
    /// <returns></returns>
    public static String StringToHex16String(String _str)
    {
        //将字符串转换成字节数组。
        byte[] buffer = System.Text.Encoding.UTF8.GetBytes(_str);
        //定义一个String类型的变量，用于存储转换后的值。
        String result = String.Empty;
        for (int i = 0; i < buffer.Length; i++)
        {
            //将每一个字节数组转换成16进制的字符串，以空格相隔开。
            result += Convert.ToString(buffer[i], 16) + " ";
        }
        return result;
    }
}