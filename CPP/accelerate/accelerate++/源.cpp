#include<iostream>
int main() {
	std::cout << "Hello World!" << std::endl;
	print::print('f');
	system("pause");
	return 0;
}
class print
{
public:
	print();
	print(char c);
	~print();

private:
	void pt();
};

print::print(char c)
{
	std::cout << c <<std::endl;
}

print::~print()
{
	std::cout << "Hi" << std::endl;
}
