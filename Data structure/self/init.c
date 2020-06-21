#include <stdio.h>
#include <stdlib.h>

typedef char datatype;
typedef struct BTree
{
    datatype data;
    struct BTree *Lchild;
    struct BTree *Rchild;
} BTnode, *BTree;

void CreateBTreeByPre(BTree *T)
{
    char c;
    scanf("%c", &c);
    if (' ' == c)
    {
        *T = NULL;
    }
    else
    {
        *T = (BTnode *)malloc(sizeof(BTnode));
        (*T)->data = c;
        CreateBTreeByPre(&(*T)->Lchild);
        CreateBTreeByPre(&(*T)->Rchild);
    }
}

void CreateBTreeByIn(BTree *T)
{
    char c;
    scanf("%c", &c);
    if (' ' == c)
    {
        *T = NULL;
    }
    else
    {
        *T = (BTnode *)malloc(sizeof(BTnode));
        CreateBTreeByIn(&(*T)->Lchild);
        (*T)->data = c;
        CreateBTreeByIn(&(*T)->Rchild);
    }
}

void CreateBTreeByPost(BTree *T)
{
    char c;
    scanf("%c", &c);
    if (' ' == c)
    {
        *T = NULL;
    }
    else
    {
        *T = (BTnode *)malloc(sizeof(BTnode));
        CreateBTreeByPost(&(*T)->Lchild);
        CreateBTreeByPost(&(*T)->Rchild);
        (*T)->data = c;
    }
}

void visit(char c)
{
    printf("%c", c);
}

void preOrder(BTree T)
{
    if (T)
    {
        visit(T->data);
        preOrder(T->Lchild);
        preOrder(T->Rchild);
    }
}

void inOrder(BTree T)
{
    if (T)
    {
        preOrder(T->Lchild);
        visit(T->data);
        preOrder(T->Rchild);
    }
}

void postOrder(BTree T)
{
    if (T)
    {
        preOrder(T->Lchild);
        preOrder(T->Rchild);
        visit(T->data);
    }
}

int main(int argc, char *argv[])
{
    BTree T = NULL;
    int choice;
    printf("Please INPUT the construct method of the Binary Tread Tree:\n(1:pre 2:in 3:post)\n");
    scanf("%d", &choice);
    switch (choice)
    {
    case 1:
        CreateBTreeByPre(&T);
        break;
    case 2:
        CreateBTreeByIn(&T);
        break;
    case 3:
        CreateBTreeByPost(&T);
        break;
    default:
        break;
    }
    printf("pre:");
        preOrder(T);
    printf("\n");
    printf("in:");
        inOrder(T);
    printf("\n");
    printf("post:");
        postOrder(T);
    return 0;
}
