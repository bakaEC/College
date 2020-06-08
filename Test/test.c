#include <stdio.h>
#include <stdlib.h>
/*
带头结点的单链表的操作
在该链表中，数据元素是int，
我们让头结点的数据域存储链表的实际长度
*/
/*链表节点的类型定义*/
struct node
{
    int data;
    struct node *next;
};
/*
链表的初始化函数
在该函数中要分配头结点存储空间
让头指针指向头结点，
因此要修改头指针的值，
所以传递头指针的地址进来
*/
void init(struct node **h)
{
    struct node *s;
    s = (struct node *)malloc(sizeof(struct node));
    if (s == NULL)
        return;
    /*
 头结点的数据域存储链表的长度
 */
    s->data = 0;
    s->next = NULL;
    /*让头指针指向头结点*/
    *h = s;
}
/*
创建链表，仍然按照逆序创建，
从后往前输入元素的值，
然后把新结点插入到表头
*/
void createLink(struct node *h)
{
    struct node *s;
    int n;
    while (1)
    {
        scanf("%d", &n);
        /*根据实际情况判断链表的元素
 输入结束
 还有一种情况就是找不到合适的
 作为结束标记的值
 先让用户输入元素个数，
 然后固定字数循环*/
        if (n == -1)
            break;
        /*
 创建新结点
 */
        s = (struct node *)malloc(sizeof(struct node));
        s->data = n;
        s->next = h->next;
        /*
 新结点放入链表的表头
 让头结点的NEXT指向新结点
 */
        h->next = s;
        (h->data)++;
    }
}
/*
遍历整个链表
*/
void bianliLink(struct node *h)
{
    int k;
    struct node *p;
    /*
 P指向第一个结点
 */
    p = h->next;
    /*
 如果定义了链表长度变量，
 可以使用变量计数，
 表示处理到链表的最后一个元素
 如果不定义链表长度变量，
 就用指针是否指向NULL，
 判断是否处理到最后一个元素了
 */
    /*1.链表长度变量计数*/
    k = 0;
    while (k < h->data)
    {
        /*对元素进行处理
   这里可以根据需求变更*/
        printf("%d ", p->data);
        /*准备处理下一个节点*/
        p = p->next;
        k++;
    }
    /*2.
 用指针判断是否处理到链表的
 最后一个元素了
 */
    printf("\n");
    p = h->next;
    while (p != NULL)
    {
        /*处理当前节点*/
        printf("%d ", p->data);
        /*准备处理下一个节点*/
        p = p->next;
    }
    printf("\n");
}
/*
int num：要插入的结点元素的值
index: 插入位置：
 index从1开始编号，
 要在Index指定的位置插入数据
*/
