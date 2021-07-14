## Entityクラスの自動生成ツール  
  
- 概要  
MySqlのテーブル情報からjavaのエンティティクラスを自動生成するツールです。  
  
- 起動方法  
EntityGeneratorApplicationクラスのmainメソッドを実行  
  
- 内部説明  
  - 起動時に「show tables」を発行しテーブル一覧を取得  
![image](https://user-images.githubusercontent.com/64537018/125593739-b6335eb8-afe9-41ad-8c84-bffaaa62e3fa.png)
  - テーブルを選んで「Generate」ボタンを押下するとテキストエリア上にエンティティクラスの定義を出力します。(指定したパスに出力できるようにしたい)  
![image](https://user-images.githubusercontent.com/64537018/125593760-ca79b03d-0357-4e11-b986-9a4eae88735e.png)
  
- Database設定
  - DataBaseConnectorクラスに定数として保持(設定ファイルから読み出すように後で変えたい)  
