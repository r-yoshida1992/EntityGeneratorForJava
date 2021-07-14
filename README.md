## Entityクラスの自動生成ツール  
  
- 概要  
MySqlのテーブル情報からjavaのエンティティクラスを自動生成するツールです。  
  
- 起動方法  
EntityGeneratorApplicationクラスのmainメソッドを実行  
  
- 内部説明  
  - 起動時に「show tables」を発行しテーブル一覧を取得  
  - テーブルを選んで「Generate」ボタンを押下するとテキストエリア上にエンティティクラスの定義を出力します。(指定したパスに出力できるようにしたい)  
  
- Database設定
  - DataBaseConnectorクラスに定数として保持(設定ファイルから読み出すように後で変えたい)  
