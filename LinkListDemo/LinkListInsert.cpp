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

	void insert_at_end();
	void insert_at_mid();
	void display();
	void insert_at_beg();
	int remove();
};

void linked :: insert_at_end()
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

void linked :: display()
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


void linked :: insert_at_mid()
{
	node *temp,*next;
	int x;

	temp=start;
	cout<<"\n Enter the Neighbour element \n";
	cin>>x;

	while(temp->d!=x)
		temp=temp->p;

	next=temp->p;
	temp->p=new node [1];
	temp=temp->p;
	temp->p=next;
	cout<<"\n Enter data to insert \n";
	cin>>temp->d;
}

void linked :: insert_at_beg()
{
	node *temp;
	temp=new node [1];
	temp->p=start;
	start=temp;

	cout<<"\n Enter element to insert \n";
	cin>>temp->d;
}

int linked :: remove()
{
	node *temp,*prev;
	int x;

	if(start==NULL)
	{
		cout<<"\n Underflow -- No element to Delete \n";
		return 0;
	}

	cout<<"\n Enter element to delete \n";
	cin>>x;

	if(start->d==x && start->p==NULL)
	{
		delete start;
		start=NULL;
		cout<<"\n Deletion successfull \n";
		return 0;
	}
	if(start->d==x)
	{
		temp=start->p;
		delete start;
		start=temp;
		cout<<"\n Deletion Successfull \n";
		return 0;
	}
	temp=start;
	while(temp->d!=x)
	{
		prev=temp;
		temp=temp->p;
	}
	prev->p=temp->p;
	cout<<"\n Deletion Successfull \n";
	return x;
}

int main()
{
	linked l;

	l.insert_at_end();
	l.insert_at_end();
	l.insert_at_end();
	l.insert_at_end();

	l.insert_at_mid();
	l.insert_at_mid();

	l.insert_at_beg();
	l.insert_at_beg();

	l.display();

	l.remove();
	l.remove();

	l.display();
	return 0;

}


