# Android 布局

[TOC]

## 帧布局

1. 放置在帧布局中的控件，在不设置方位和边距的情况下，默认放在左上角。
2. 帧布局中的控件可以重叠放置。
3. 帧布局控件设置屏幕中方位使用**layout_gravity**属性（四方位）。属性值center/center_vertical/center_horizontal对应居中类型。
4. 可以链接居中位置和对齐位置，用`|`链接
5. 添加margin控制边距

## 线性布局

1. 方向属性：Orientation(**vertical**/**horizontal**)。[horizontal水平、vertical垂直]
2. 当线性布局为`垂直/水平`方向时，线性布局中的控件以`从上到下/从左到右`的方式堆放。堆放方式是无空隙的。     
3. **设置layout_gravity时只能实现与布局方向垂直的对齐方向和对齐方向。**`（期中）`
4. 可以通过垂直方向的相同方向的margin控制控件之间的距离。
5. 比重属性weight：可以通过weight属性分配布局占比比例。

## 网格布局

> 在Android 5.0 后推出

1. 网格布局有方向oriental属性[horizontal/vertical]，代表不同方向的网格布局，当一列/行放满后换行放置。

2. 网格布局中的组件不用设置layout_height和layout_width，但不代表高度和宽度属性不存在，网格布局中的组件预先已经被Grid Layout的宽高进行行数和列数的等比例划分。

3. columnCount/rowCount设置网格布局中组件的列数/行数

4. layout_row设置网格布局中组件的行数（从0开始），layout_column设置网格布局组件的列数，被设置行数和列数的组件，排在该组件之后的组件，紧接着该组件位置按序放置。排在该组件之前的位置不变。

5. `layout_rowWeight`和`layout_columnWeight`

   

