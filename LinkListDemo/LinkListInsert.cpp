/*
 * LinkListInsert.cpp
 *
 *  Created on: May 8, 2014
 *      Author: Ashish
 */
#include<iostream>
using namespace std;

class node
{
	public : int d;
	node *p;
};

class linked
{
	public :
	node *start;

	public :
	linked()
	{
		start=NULL;
	}


	void insert_at_end()
	{
		node *temp;

		if(start==NULL)
		{
			start=new node [1];
			start->p=NULL;
			cout<<"\n Enter Data to insert \n";
			cin>>start->d;
		}
		else
		{
			temp=start;
			while(temp->p!=NULL)
				temp=temp->p;

			temp->p= new node [1];
			temp=temp->p;

			cout<<"\n Enter Data to insert \n";
			cin>>temp->d;
			temp->p=NULL;
		}
	}

	void display()
	{
		node *temp;
		temp=start;

		cout<<"\n Linked List elements are \n";

		while(temp->p!=NULL)
		{
			cout<<""<<temp->d<<" ";
			temp=temp->p;
		}
		cout<<temp->d<<endl;
	}

};


int main()
{
	int n=0,a;
	linked l;

	l.insert_at_end();
	l.insert_at_end();
	l.insert_at_end();
	l.insert_at_end();

	l.display();
	return 0;

}


