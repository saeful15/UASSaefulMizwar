package saefulProjectSpringBoot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import saefulProjectSpringBoot.dao.TabunganDao;
import saefulProjectSpringBoot.model.TabunganModel;
@RestController
@RequestMapping("/Bank")
public class TabunganController {
	@Autowired
	TabunganDao tabungandao;
	@RequestMapping("/TabunganModel")
	public TabunganModel saveTabungan(@Valid @RequestBody TabunganModel book) {
		List<TabunganModel>b=tabungandao.getAll();
		int saldo=0;
		if(b.size()>0) {
			for(int x=0;x<b.size();x++) {
				if(book.getNik().equals(b.get(x).getNik())) {
					saldo=b.get(x).getSaldo();
				}
			}
		}
		if(book.getDebit()>0) {
			saldo-=book.getDebit();
		}
		else {
			saldo+=book.getkredit();
		}
		book.setSaldo(saldo);
		return tabungandao.saveTabungan(book); 
	}
	@GetMapping("/TabunganModel")
	public List<TabunganModel> getAllTabungan(){
		return tabungandao.getAll();
	}

	@GetMapping("/GetById/{id}")
	public ResponseEntity<TabunganModel> getOneTabungan(@PathVariable( value="id") Long id){
		TabunganModel b=tabungandao.getsatu(id);
		if(b==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok().body(b);
		}
	}
	@GetMapping("/GetByNik/{nik}")
	public List <TabunganModel> getOneNik(@PathVariable( value="nik") String nik){
		return tabungandao.getnik(nik);
	}

	@PutMapping("/TabunganModel/{id}")
	public ResponseEntity<TabunganModel> updateBook(@PathVariable( value="id") Long id,@Valid @RequestBody TabunganModel book){
		TabunganModel b=tabungandao.getsatu(id);
		if(b==null) {
			return ResponseEntity.notFound().build();
		}
		else {
		b.setDebit(book.getDebit());
		b.setkredit(book.getkredit());
		TabunganModel bResult=tabungandao.saveTabungan(b);
		List<TabunganModel> s= tabungandao.getnik(b.getNik());
		int saldo=0;
		for(int x=0;x<s.size();x++) {
			TabunganModel c=tabungandao.getsatu(s.get(x).getId());
			if(b.getNik().equals(s.get(x).getNik())) {
				saldo-=s.get(x).getDebit();
				saldo+=s.get(x).getkredit();
				c.setSaldo(saldo);
			}
			tabungandao.saveTabungan(c);
		}
		return ResponseEntity.ok().body(bResult);
		}
	}
	
	@DeleteMapping("/TabunganModel/{id}")
	public ResponseEntity<TabunganModel> deleteTabungan(@PathVariable( value="id") Long id){
		TabunganModel b=tabungandao.getsatu(id);
	if(b==null) {
		return ResponseEntity.notFound().build();
	}
	else {
		tabungandao.tabunganDelete(id);

		List<TabunganModel> s= tabungandao.getnik(b.getNik());
		int saldo=0;
		for(int x=0;x<s.size();x++) {
			TabunganModel c=tabungandao.getsatu(s.get(x).getId());
			if(b.getNik().equals(s.get(x).getNik())) {
				saldo-=s.get(x).getDebit();
				saldo+=s.get(x).getkredit();
				c.setSaldo(saldo);
			}
			tabungandao.saveTabungan(c);
		}
		return ResponseEntity.ok().build();
	}
	}
}
