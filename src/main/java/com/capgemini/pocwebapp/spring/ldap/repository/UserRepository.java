package com.capgemini.pocwebapp.spring.ldap.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.core.support.BaseLdapNameAware;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

import com.capgemini.pocwebapp.beans.LdapUser;

import javax.naming.Name;
import javax.naming.directory.*;
import javax.naming.ldap.LdapName;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Service
@PropertySource("classpath:/config/ldap/ldap_details.properties")
public class UserRepository implements BaseLdapNameAware {

    @Autowired
    private LdapTemplate ldapTemplate;
    
    @Autowired
	private Environment env;
    
    private LdapName baseLdapPath;

    public void setBaseLdapPath(LdapName baseLdapPath) {
        this.baseLdapPath = baseLdapPath;
    }


    public void create(LdapUser p) {
        Name dn = buildDn(p);        
        ldapTemplate.bind(LdapUtils.emptyLdapName(), null, buildAttributes(p));

       /* DirContextAdapter context = new DirContextAdapter(dn);
        context.setAttributeValues("objectclass", new String[] {"top", "inetOrgPerson"});
        context.setAttributeValue("cn", p.getFullName());
        context.setAttributeValue("sn", p.getLastName());
        context.setAttributeValue("description","Test");
        ldapTemplate.bind(context);*/
    }

    public List<LdapUser> findAll() {
        EqualsFilter filter = new EqualsFilter("objectclass", "inetOrgPerson");
        return ldapTemplate.search(LdapUtils.emptyLdapName(), filter.encode(), new PersonContextMapper());
    }

    public LdapUser findOne(String uid) {
        Name dn = LdapNameBuilder.newInstance(baseLdapPath)
                .add("ou", "people")
                .add("uid", uid)
                .build();
        return ldapTemplate.lookup(dn, new PersonContextMapper());
    }

    public List<LdapUser> findByName(String name) {
        LdapQuery q = query()
                .where("objectclass").is("person")
                .and("cn").whitespaceWildcardsLike(name);
        return ldapTemplate.search(q, new PersonContextMapper());
    }

    public void update(LdapUser p) {
        ldapTemplate.rebind(buildDn(p), null, buildAttributes(p));
    }

    public void updateLastName(LdapUser p) {
        Attribute attr = new BasicAttribute("sn", p.getLastName());
        ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);
        ldapTemplate.modifyAttributes(buildDn(p), new ModificationItem[] {item});
    }

    public void delete(LdapUser p) {
        ldapTemplate.unbind(buildDn(p));
    }

    private Name buildDn(LdapUser p) {
    	baseLdapPath = LdapUtils.newLdapName(env.getRequiredProperty("ldap.user"));
        return LdapNameBuilder.newInstance(baseLdapPath)
                .add("ou", "people")
                //.add("uid", p.getUid())
                .build();
    }

    private Attributes buildAttributes(LdapUser p) {
        Attributes attrs = new BasicAttributes();
        BasicAttribute ocAttr = new BasicAttribute("objectclass");
        ocAttr.add("top");
        ocAttr.add("inetOrgPerson");
        attrs.put(ocAttr);
        attrs.put("ou", "people");
        attrs.put("uid", p.getUid());
        attrs.put("cn", p.getFullName());
        attrs.put("sn", p.getLastName());
        attrs.put("homeDirectory", "LBS");
        return attrs;
    }


    private static class PersonContextMapper extends AbstractContextMapper<LdapUser> {
        public LdapUser doMapFromContext(DirContextOperations context) {
        	LdapUser person = new LdapUser();
            person.setFullName(context.getStringAttribute("cn"));
            person.setLastName(context.getStringAttribute("sn"));
            person.setUid(context.getStringAttribute("uid"));
            person.setEmail(context.getStringAttribute("mail"));
            //System.out.println(" RoleNames :"+context.getStringAttributes("memberof"));
            return person;
        }
    }
}
